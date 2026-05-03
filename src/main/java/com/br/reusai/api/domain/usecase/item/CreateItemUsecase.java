package com.br.reusai.api.domain.usecase.item;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;

public interface CreateItemUsecase {
    CreateItemResponse execute(Item item);
}
