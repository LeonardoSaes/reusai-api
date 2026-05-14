package com.br.reusai.api.host.controller.converter;

import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.host.controller.data.request.CreateUserRequest;
import com.br.reusai.api.host.controller.data.response.CreateUserResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public abstract class UserControllerConverter {

    public CreateUserResponse toCreateUserResponse(String idUser) {
        return new CreateUserResponse(idUser);
    }

    public User toDomain(CreateUserRequest createUserRequest) {
        User user = new User(
            createUserRequest.username(),
            createUserRequest.email(),
            createUserRequest.password(),
            createUserRequest.photoUrl(),
            createUserRequest.cep(),
            createUserRequest.items()
        );

        // Se forem enviados nos campos, sobrescreve os defaults
        if (createUserRequest.accountNonExpired() != null) {
            user.setAccountNonExpired(createUserRequest.accountNonExpired());
        }
        if (createUserRequest.accountNonLocked() != null) {
            user.setAccountNonLocked(createUserRequest.accountNonLocked());
        }
        if (createUserRequest.credentialsNonExpired() != null) {
            user.setCredentialsNonExpired(createUserRequest.credentialsNonExpired());
        }
        if (createUserRequest.enabled() != null) {
            user.setEnabled(createUserRequest.enabled());
        }

        return user;
    }
}
