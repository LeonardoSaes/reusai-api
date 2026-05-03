package com.br.reusai.api.domain.usecase.user.impl;

import com.br.reusai.api.domain.exception.BusinessException;
import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.domain.usecase.user.CreateUserUsecase;
import com.br.reusai.api.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_CONTENT;

@Component
@RequiredArgsConstructor
public class CreateUserUsecaseImpl implements CreateUserUsecase {

    private final UserGateway userGateway;
    private final GetUserByEmailUsecaseImpl getUserByEmailUsecase;

    @Override
    public String execute(User user) {
        User existUser = getUserByEmailUsecase.execute(user.getEmail());
        if (existUser != null) {
            throw new BusinessException(UNPROCESSABLE_CONTENT.value(), "Already exist an user with this email");
        }
        return userGateway.createUser(user);
    }
}
