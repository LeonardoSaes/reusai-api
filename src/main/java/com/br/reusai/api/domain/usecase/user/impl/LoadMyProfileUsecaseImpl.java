package com.br.reusai.api.domain.usecase.user.impl;

import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.domain.usecase.user.LoadMyProfileUsecase;
import com.br.reusai.api.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadMyProfileUsecaseImpl implements LoadMyProfileUsecase {

    private final UserGateway userGateway;

    @Override
    public User execute(String userId) {
        return userGateway.getUserById(userId);
    }
}
