package com.br.reusai.api.gateway;

import org.springframework.web.multipart.MultipartFile;

public interface ImagesGateway {
    String uploadImageToSupabaseStorage(MultipartFile file) throws Exception;
}
