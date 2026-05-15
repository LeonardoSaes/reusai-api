package com.br.reusai.api.domain.usecase.item.impl;

import com.br.reusai.api.domain.usecase.image.SupabaseUsecase;
import com.br.reusai.api.domain.usecase.item.DeleteItemUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteItemUsecaseImpl implements DeleteItemUsecase {

    private final ItemGateway itemGateway;
    private final SupabaseUsecase supabaseUsecase;


    @Override
    public void execute(String id) {
//        supabaseUsecase.

        itemGateway.deleteItem(id);
        log.info("Item deleted successfully");
    }
}
