package com.br.reusai.api.domain.usecase.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.UpdateItemUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UpdateItemUsecaseImpl implements UpdateItemUsecase {

    private final ItemGateway itemGateway;

    @Override
    public void execute(String id, Item item) {

        Item existItem = itemGateway.getItemById(id);

        if(isNull(existItem)){
            throw new RuntimeException("Item not exist");
        }

        existItem.setUpdatedAt(LocalDateTime.now());
        existItem.setCreatedAt(LocalDateTime.now());
        existItem.setCategory(item.getCategory());
        existItem.setDescription(item.getDescription());
        existItem.setStatus(item.getStatus());
        existItem.setImageUrl(item.getImageUrl());
        existItem.setTitle(item.getTitle());
        itemGateway.updateItem(existItem);
    }
}
