package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.CreateItemUsecase;
import com.br.reusai.api.domain.usecase.DeleteItemUsecase;
import com.br.reusai.api.domain.usecase.UpdateItemUsecase;
import com.br.reusai.api.host.controller.converter.ItemControllerConverter;
import com.br.reusai.api.host.controller.data.request.CreateItemRequest;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemControllerConverter itemControllerConverter;
    private final CreateItemUsecase createItemUsecase;
    private final UpdateItemUsecase updateItemUsecase;
    private final DeleteItemUsecase deleteItemUsecase;

    @PostMapping
    public ResponseEntity<CreateItemResponse> createItem(@RequestBody CreateItemRequest createItemRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createItemUsecase.execute(itemControllerConverter.toDomain(createItemRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> UpdateItem(@PathVariable String id, @RequestBody CreateItemRequest createItemRequest) {
        updateItemUsecase.execute(id, itemControllerConverter.toDomain(createItemRequest));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        deleteItemUsecase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
