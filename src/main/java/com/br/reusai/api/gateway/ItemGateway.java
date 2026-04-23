package com.br.reusai.api.gateway;

import com.br.reusai.api.domain.model.Item;

public interface ItemGateway {
    String createItem(Item item);
    Item getItemPerTitle(String title);
    void updateItem(Item item);
    Item getItemById(String id);


}
