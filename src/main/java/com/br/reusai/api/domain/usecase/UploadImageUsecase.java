package com.br.reusai.api.domain.usecase;

import com.br.reusai.api.domain.model.Image;

public interface UploadImageUsecase {
    String execute(String url, Image image);
}
