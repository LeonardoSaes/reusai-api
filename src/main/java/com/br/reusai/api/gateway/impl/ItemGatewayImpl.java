package com.br.reusai.api.gateway.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.gateway.ItemGateway;
import com.br.reusai.api.gateway.converter.ItemGatewayConverter;
import com.br.reusai.api.gateway.mysql.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemGatewayImpl implements ItemGateway {

    private final ItemRepository itemRepository;
    private final ItemGatewayConverter itemGatewayConverter;

    @Override
    public String createItem(Item item) {
        return itemRepository.save(itemGatewayConverter.toEntity(item)).getId().toString();
    }

    @Override
    public Item getItemPerTitle(String title) {
        return itemGatewayConverter.toDomain(itemRepository.findItemByTitle(title));
    }
}
