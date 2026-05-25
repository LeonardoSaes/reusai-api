package com.br.reusai.api.domain.usecase.item.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.item.GetItemsNotMineUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetItemsNotMineUsecaseImpl implements GetItemsNotMineUsecase {

    private final ItemGateway itemGateway;

    @Override
    public List<Item> execute(String userId) {
        return itemGateway.getItemsNotMine(userId);
    }
}
