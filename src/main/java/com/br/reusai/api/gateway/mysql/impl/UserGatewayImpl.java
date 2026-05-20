package com.br.reusai.api.gateway.mysql.impl;

import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.gateway.UserGateway;
import com.br.reusai.api.gateway.converter.UserGatewayConverter;
import com.br.reusai.api.gateway.mysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserGatewayConverter userGatewayConverter;

    @Override
    public String createUser(User user) {
        return userRepository.save(userGatewayConverter.toEntity(user)).getId();
    }

    @Override
    public User getUserByEmail(String email) {
        var userEntity = userRepository.findUserByEmail(email);
        return userEntity != null ? userGatewayConverter.toDomain(userEntity) : null;
    }

    @Override
    public UserDetails getUserDetailsByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUserById(String id) {
        return userGatewayConverter.toDomain(userRepository.findUserById(id));
    }
}
