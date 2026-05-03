package com.br.reusai.api.domain.usecase.item;

import com.br.reusai.api.domain.model.Item;

public interface UpdateItemUsecase {
    void execute(String id, Item item);
}
