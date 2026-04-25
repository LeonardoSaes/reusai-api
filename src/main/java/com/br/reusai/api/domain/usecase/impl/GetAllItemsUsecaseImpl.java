package com.br.reusai.api.domain.usecase.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.GetAllItemsUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllItemsUsecaseImpl implements GetAllItemsUsecase {

    private final ItemGateway itemGateway;

    @Override
    public List<Item> execute() {
        return itemGateway.getAllItems();
    }
}
