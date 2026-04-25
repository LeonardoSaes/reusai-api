package com.br.reusai.api.domain.usecase;

import com.br.reusai.api.domain.model.Item;
import java.util.List;

public interface GetAllItemsUsecase {
        List<Item> execute();
}
