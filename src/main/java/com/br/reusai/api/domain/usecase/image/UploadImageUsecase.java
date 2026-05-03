package com.br.reusai.api.domain.usecase.image;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageUsecase {
    String execute(MultipartFile file);
}
