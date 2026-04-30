package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.UploadImageUsecase;
import com.br.reusai.api.host.controller.data.response.UploadImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UploadImageController {

    private final UploadImageUsecase uploadImageUsecase;

    @PostMapping("/upload-image")
    public ResponseEntity<UploadImageResponse> uploadImage(@RequestParam("file") MultipartFile file){
        return ResponseEntity.status(HttpStatus.OK).body(new UploadImageResponse(
                uploadImageUsecase.execute(file),
                "Upload realizado com sucesso"));
    }
}
