package com.br.reusai.api.domain.usecase.user.impl;

import com.br.reusai.api.domain.exception.BusinessException;
import com.br.reusai.api.domain.model.User;
import com.br.reusai.api.domain.usecase.user.CreateUserUsecase;
import com.br.reusai.api.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                "",
                8,
                185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
        );

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        var encodedPassword = passwordEncoder.encode(user.getPassword());
        if (!encodedPassword.startsWith("{pbkdf2}")) {
            encodedPassword = "{pbkdf2}" + encodedPassword;
        }

        user.setPassword(encodedPassword);
        return userGateway.createUser(user);
    }
}
