package com.br.reusai.api.gateway.converter;

import com.br.reusai.api.domain.model.Image;
import com.br.reusai.api.gateway.postgre.entity.ImagesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImagesGatewayConverter {
        ImagesEntity toImagesEntity(Image image);
    }
