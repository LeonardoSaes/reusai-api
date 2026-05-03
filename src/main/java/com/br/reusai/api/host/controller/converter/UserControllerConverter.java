package com.br.reusai.api.host.controller.converter;

import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.host.controller.data.request.CreateUserRequest;
import com.br.reusai.api.host.controller.data.response.CreateUserResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserControllerConverter {
    CreateUserResponse toCreateUserResponse(String idUser);
    User toDomain(CreateUserRequest createUserRequest);
}
