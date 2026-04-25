package com.br.reusai.api.domain.usecase.impl;

import com.br.reusai.api.domain.exception.ImageUploadException;
import com.br.reusai.api.domain.usecase.UploadImageUsecase;
import com.br.reusai.api.gateway.impl.ImagesGatewayImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadImageUsecaseImpl implements UploadImageUsecase {

    private final ImagesGatewayImpl imagesGateway;

    @Override
    public String execute(MultipartFile file) {
        try{
            String publicUrl = imagesGateway.uploadImageToSupabaseStorage(file);
            log.info("Imagem salva com sucesso: {}", publicUrl);
            return publicUrl;
        } catch (Exception e){
            log.error("Erro ao fazer upload da imagem", e);
            throw new ImageUploadException("Erro ao fazer upload: " + e.getMessage());
        }
    }
}
