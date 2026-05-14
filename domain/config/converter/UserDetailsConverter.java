package com.br.reusai.api.domain.config.converter;

import com.br.reusai.api.gateway.mysql.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter {

    public UserEntity toUserEntity(UserDetails userDetails) {
        if (userDetails instanceof UserEntity) {
            return (UserEntity) userDetails;
        }
        throw new IllegalArgumentException("UserDetails must be an instance of UserEntity");
    }
}
