package com.br.reusai.api.domain.usecase.item.impl;

import com.br.reusai.api.domain.exception.BusinessException;
import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.item.GetItemByIdUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_CONTENT;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetItemByIdUsecaseImpl implements GetItemByIdUsecase {

    private final ItemGateway itemGateway;

    @Override
    public Item getItemById(String userId) {
        Item item = itemGateway.getItemById(userId);
        if(isNull(item)){
            log.error("Item not exist");
            throw new BusinessException(UNPROCESSABLE_CONTENT.value(), "Item not exist");
        }
        return itemGateway.getItemById(userId);
    }
}
