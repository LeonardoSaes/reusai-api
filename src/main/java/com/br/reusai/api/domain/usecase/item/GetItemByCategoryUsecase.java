package com.br.reusai.api.domain.usecase.item;

import com.br.reusai.api.domain.model.Item;
import java.util.List;

public interface GetItemByCategoryUsecase {
    List<Item> execute(String category);
}
