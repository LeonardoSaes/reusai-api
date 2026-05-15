package com.br.reusai.api.domain.usecase.item.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.item.CreateItemUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateItemUsecaseImpl implements CreateItemUsecase {

    private final ItemGateway itemGateway;

    @Override
    public CreateItemResponse execute(Item item){

        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());
        log.info("Item created successfully");
        return itemGateway.createItem(item);
    }
}
