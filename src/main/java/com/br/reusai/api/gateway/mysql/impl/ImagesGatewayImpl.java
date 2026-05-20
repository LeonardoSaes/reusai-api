/*
package com.br.reusai.api.gateway.mysql.impl;

import com.br.reusai.api.domain.exception.ImageUploadException;
import com.br.reusai.api.domain.model.Image;
import com.br.reusai.api.domain.usecase.image.SupabaseUsecase;
import com.br.reusai.api.gateway.ImagesGateway;
import com.br.reusai.api.gateway.converter.ImagesGatewayConverter;
import com.br.reusai.api.gateway.postgre.entity.ImagesEntity;
import com.br.reusai.api.gateway.postgre.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImagesGatewayImpl implements ImagesGateway {

    private final ImagesRepository imagesRepository;
    private final ImagesGatewayConverter imagesGatewayConverter;
    private final SupabaseUsecase supabaseUsecase;

    @Override
    @Transactional(transactionManager = "postgreSQLTransactionManager")
    public String uploadImageToSupabaseStorage(MultipartFile file) throws Exception{
        try {
            // Fazer upload no Supabase Storage
            String publicUrl = supabaseUsecase.execute(file);
            log.info("Upload no Supabase Storage realizado com sucesso. URL: {}", publicUrl);

            // Criar e salvar registro no banco
            Image image = new Image();
            image.setUrl(publicUrl);
            image.setCreatedAt(java.time.LocalDateTime.now());

            ImagesEntity imagesEntity = imagesGatewayConverter.toImagesEntity(image);
            ImagesEntity saved = imagesRepository.save(imagesEntity);

            log.info("Imagem salva no banco com ID: {}", saved.getId());
            return publicUrl;

        } catch (Exception e) {
            log.error("Erro ao fazer upload da imagem", e);
            throw new ImageUploadException("Erro ao fazer upload: " + e.getMessage());
        }
    }
}
*/
