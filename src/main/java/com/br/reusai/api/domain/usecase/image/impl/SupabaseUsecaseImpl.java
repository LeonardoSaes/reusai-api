package com.br.reusai.api.domain.usecase.image.impl;

import com.br.reusai.api.domain.exception.BusinessException;
import com.br.reusai.api.domain.usecase.image.SupabaseUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_CONTENT;

@Slf4j
@Component
@RequiredArgsConstructor
public class SupabaseUsecaseImpl implements SupabaseUsecase {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.bucket}")
    private String bucketName;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public String execute(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String sanitizedFilename = originalFilename.replaceAll("\\s+", "_");
            String fileName = UUID.randomUUID() + "_" + sanitizedFilename;

            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            String uploadUrl = String.format("%s/storage/v1/object/%s/%s", supabaseUrl, bucketName, encodedFileName);
            log.info("Upload URL: {}", uploadUrl);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uploadUrl))
                    .header("Authorization", "Bearer " + supabaseKey)
                    .header("Content-Type", file.getContentType() != null ? file.getContentType() : "application/octet-stream")
                    .header("x-upsert", "false")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(file.getBytes()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                log.error("Erro ao fazer upload. Status: {}, Resposta: {}", response.statusCode(), response.body());
                throw new IOException("Erro ao fazer upload: " + response.statusCode() + " - " + response.body());
            }

            log.info("Upload realizado com sucesso");

            String publicUrl = String.format("%s/storage/v1/object/public/%s/%s", supabaseUrl, bucketName, encodedFileName);

            return publicUrl;
        } catch (Exception e) {
            log.error("Erro ao fazer upload para Supabase Storage", e);
            throw new BusinessException(UNPROCESSABLE_CONTENT.value(),"Erro ao fazer upload para Supabase Storage: " + e.getMessage());
        }
    }
}
