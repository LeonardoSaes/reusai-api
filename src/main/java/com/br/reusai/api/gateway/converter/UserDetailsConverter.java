package com.br.reusai.api.gateway.converter;

import com.br.reusai.api.gateway.mysql.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserDetailsConverter {

    default UserEntity toUserEntity(UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }

        if (userDetails instanceof UserEntity) {
            return (UserEntity) userDetails;
        }

        return null;
    }
}
