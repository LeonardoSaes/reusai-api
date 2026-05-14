package com.br.reusai.api.gateway.converter;

import com.br.reusai.api.domain.model.Image;
import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.gateway.mysql.entity.ItemEntity;
import com.br.reusai.api.gateway.postgre.entity.ImagesEntity;
import com.br.reusai.api.host.controller.data.response.CreateItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemGatewayConverter {

    @Mapping(source = "user.id", target = "idUser")
    Item toDomain(ItemEntity itemEntity);

    @Mapping(target = "user", ignore = true)
    ItemEntity toEntity(Item item);

    @Mapping(source = "user.id", target = "userId")
    CreateItemResponse toCreateItemResponse(ItemEntity itemEntity);

    ImagesEntity toImagesEntity(Image image);
}
