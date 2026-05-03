package com.br.reusai.api.gateway.mysql.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.gateway.ItemGateway;
import com.br.reusai.api.gateway.converter.ItemGatewayConverter;
import com.br.reusai.api.gateway.mysql.repository.ItemRepository;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemGatewayImpl implements ItemGateway {

    private final ItemRepository itemRepository;
    private final ItemGatewayConverter itemGatewayConverter;

    @Override
    public CreateItemResponse createItem(Item item) {
        return itemGatewayConverter.toCreateItemResponse(itemRepository.save(itemGatewayConverter.toEntity(item)));
    }

    @Override
    public Item getItemPerTitle(String title) {
        return itemGatewayConverter.toDomain(itemRepository.findItemByTitle(title));
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.save(itemGatewayConverter.toEntity(item));
    }

    @Override
    public Item getItemById(String id) {
        return itemGatewayConverter.toDomain(itemRepository.findItemById(id));
    }

    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll().stream().map(itemGatewayConverter::toDomain).toList();
    }

    @Override
    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findItemByCategory(category).stream().map(itemGatewayConverter::toDomain).toList();
    }
}
