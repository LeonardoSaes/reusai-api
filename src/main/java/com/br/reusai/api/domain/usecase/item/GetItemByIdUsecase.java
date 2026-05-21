package com.br.reusai.api.domain.usecase.item;

import com.br.reusai.api.domain.model.Item;

public interface GetItemByIdUsecase {
    Item getItemById(String userId);
}
