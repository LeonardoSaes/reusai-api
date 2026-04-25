package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.*;
import com.br.reusai.api.host.controller.converter.ItemControllerConverter;
import com.br.reusai.api.host.controller.data.request.CreateItemRequest;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;
import com.br.reusai.api.host.controller.data.response.UploadImageResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemControllerConverter itemControllerConverter;
    private final CreateItemUsecase createItemUsecase;
    private final UpdateItemUsecase updateItemUsecase;
    private final DeleteItemUsecase deleteItemUsecase;
    private final UploadImageUsecase uploadImageUsecase;
    private final GetAllItemsUsecase getAllItemsUsecase;
    private final GetItemByCategoryUsecase getItemByCategoryUsecase;

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

    @PostMapping("/upload-image")
    public ResponseEntity<UploadImageResponse> uploadImage(@RequestParam("file") MultipartFile file){
        return ResponseEntity.status(HttpStatus.OK).body(new UploadImageResponse(
                uploadImageUsecase.execute(file),
                "Upload realizado com sucesso"));
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.status(HttpStatus.OK).body(getAllItemsUsecase.execute());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(getItemByCategoryUsecase.execute(category));
    }
}
