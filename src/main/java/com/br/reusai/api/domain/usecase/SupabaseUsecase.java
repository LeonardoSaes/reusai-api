package com.br.reusai.api.domain.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface SupabaseUsecase {
    String execute(MultipartFile file);
}
