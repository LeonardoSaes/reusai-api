package com.br.reusai.api.domain.usecase.user.impl;

import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.domain.usecase.user.GetUserByEmailUsecase;
import com.br.reusai.api.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserByEmailUsecaseImpl implements GetUserByEmailUsecase {

    private final UserGateway userGateway;

    @Override
    public User execute(String email) {
        return userGateway.getUserByEmail(email);
    }
}
