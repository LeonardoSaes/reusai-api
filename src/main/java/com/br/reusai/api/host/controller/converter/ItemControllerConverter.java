package com.br.reusai.api.host.controller.converter;

import com.br.reusai.api.domain.model.Item;
import com.br.reusai.api.host.controller.data.request.CreateItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemControllerConverter {
    Item toDomain(CreateItemRequest createItemRequest);
}
