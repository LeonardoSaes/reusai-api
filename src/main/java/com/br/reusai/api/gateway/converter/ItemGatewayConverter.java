package com.br.reusai.api.gateway.converter;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.gateway.mysql.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemGatewayConverter {
    Item toDomain(ItemEntity itemEntity);
    ItemEntity toEntity(Item item);

}
