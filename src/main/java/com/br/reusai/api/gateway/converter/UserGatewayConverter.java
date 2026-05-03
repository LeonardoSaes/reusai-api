package com.br.reusai.api.gateway.converter;

import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.gateway.mysql.entity.UserEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserGatewayConverter {
    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User user);
}
