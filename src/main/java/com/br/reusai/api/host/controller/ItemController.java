package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.CreateItemUsecase;
import com.br.reusai.api.host.controller.converter.ItemControllerConverter;
import com.br.reusai.api.host.controller.data.request.CreateItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemControllerConverter itemControllerConverter;
    private final CreateItemUsecase createItemUsecase;

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody CreateItemRequest createItemRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createItemUsecase.execute(itemControllerConverter.toDomain(createItemRequest)));
    }
}
