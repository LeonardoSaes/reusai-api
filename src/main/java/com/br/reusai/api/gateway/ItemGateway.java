package com.br.reusai.api.gateway;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;

public interface ItemGateway {
    CreateItemResponse createItem(Item item);
    Item getItemPerTitle(String title);
    void updateItem(Item item);
    Item getItemById(String id);
    void deleteItem(String id);
}
