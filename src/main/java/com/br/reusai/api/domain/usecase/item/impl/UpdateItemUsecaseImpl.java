package com.br.reusai.api.domain.usecase.item.impl;

import com.br.reusai.api.domain.exception.BusinessException;
import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.item.UpdateItemUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_CONTENT;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateItemUsecaseImpl implements UpdateItemUsecase {

    private final ItemGateway itemGateway;

    @Override
    public void execute(String id, Item item) {

        Item existItem = itemGateway.getItemById(id);

        if(isNull(existItem)){
            log.error("Item not exist");
            throw new BusinessException(UNPROCESSABLE_CONTENT.value(), "Item not exist");
        }

        existItem.setUpdatedAt(LocalDateTime.now());
        existItem.setCreatedAt(LocalDateTime.now());
        existItem.setCategory(item.getCategory());
        existItem.setDescription(item.getDescription());
        existItem.setStatus(item.getStatus());
        existItem.setImageUrl(item.getImageUrl());
        existItem.setTitle(item.getTitle());
        existItem.setAvailableToChange(item.getAvailableToChange());
        itemGateway.updateItem(existItem);
        log.info("Item updated successfully");
    }
}
