package com.br.reusai.api.domain.usecase.item.impl;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.domain.usecase.image.DeleteSupabaseImageUsecase;
import com.br.reusai.api.domain.usecase.item.DeleteItemUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteItemUsecaseImpl implements DeleteItemUsecase {

    private final ItemGateway itemGateway;
    private final DeleteSupabaseImageUsecase deleteSupabaseImageUsecase;

    private static final String PUBLIC_OBJECT_PREFIX = "/storage/v1/object/public/";

    @Override
    public void execute(String id) {

        Item item = itemGateway.getItemById(id);
        if (isNull(item)) {
            log.warn("Item not found for id={}", id);
            return;
        }

        String storedImage = item.getImageUrl();
        String pathImage = extractSupabasePath(storedImage);

        if (pathImage != null && !pathImage.isBlank()) {
            deleteSupabaseImageUsecase.execute(pathImage);
        } else {
            log.info("Skipping image deletion because imageUrl/path is empty for item id={}", id);
        }

        itemGateway.deleteItem(id);
        log.info("Item deleted successfully");
    }

    static String extractSupabasePath(String imageUrlOrPath) {
        if (imageUrlOrPath == null) return null;
        String value = imageUrlOrPath.trim();
        if (value.isEmpty()) return null;

        int publicIdx = value.indexOf(PUBLIC_OBJECT_PREFIX);
        if (publicIdx < 0) {
            return value;
        }

        String afterPrefix = value.substring(publicIdx + PUBLIC_OBJECT_PREFIX.length());
        int firstSlash = afterPrefix.indexOf('/');
        if (firstSlash < 0 || firstSlash == afterPrefix.length() - 1) {
            return null;
        }

        // Remove bucket name
        return afterPrefix.substring(firstSlash + 1);
    }
}
