package com.br.reusai.api.gateway.converter;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.gateway.mysql.entity.ItemEntity;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemGatewayConverter {
    Item toDomain(ItemEntity itemEntity);
    ItemEntity toEntity(Item item);
    CreateItemResponse toCreateItemResponse(ItemEntity itemEntity);

}
