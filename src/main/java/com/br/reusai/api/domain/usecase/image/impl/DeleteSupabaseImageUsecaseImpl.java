package com.br.reusai.api.domain.usecase.image.impl;

import com.br.reusai.api.domain.usecase.image.DeleteSupabaseImageUsecase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeleteSupabaseImageUsecaseImpl implements DeleteSupabaseImageUsecase {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.bucket}")
    private String bucketName;


    private final RestTemplate restTemplate;

    public DeleteSupabaseImageUsecaseImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(String pathImage) {
        String imageUrl = String.format("%s/storage/v1/object/%s/%s", supabaseUrl, bucketName, pathImage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + supabaseKey);
        headers.set("apikey", supabaseKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                imageUrl,
                HttpMethod.DELETE,
                entity,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao deletar imagem do Supabase: " + response.getBody());
        }
    }
}
