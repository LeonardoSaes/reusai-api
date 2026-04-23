package com.br.reusai.api.domain.usecase.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.CreateItemUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CreateItemUsecaseImpl implements CreateItemUsecase {

    private final ItemGateway itemGateway;

    @Override
    public String execute(Item item) throws RuntimeException{
        Item existItem = itemGateway.getItemPerTitle(item.getTitle());

//        if(isNull(existItem)){
//            throw new RuntimeException("Item not exist");
//        }
        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());
        return itemGateway.createItem(item);
    }
}
