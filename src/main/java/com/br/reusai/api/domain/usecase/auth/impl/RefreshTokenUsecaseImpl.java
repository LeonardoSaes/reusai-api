package com.br.reusai.api.domain.usecase.auth.impl;

import com.br.reusai.api.domain.config.JwtTokenProvider;
import com.br.reusai.api.domain.usecase.auth.RefreshTokenUsecase;
import com.br.reusai.api.gateway.UserGateway;
import com.br.reusai.api.host.controller.data.response.security.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
public class RefreshTokenUsecaseImpl implements RefreshTokenUsecase {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserGateway userGateway;

    @Override
    public TokenDTO execute(String email, String refreshToken) {
        if (isBlank(email) || isBlank(refreshToken)) {
            throw new UsernameNotFoundException("Invalid email or token");
        }

        var user = userGateway.getUserByEmail(email);
        TokenDTO token;
        if (user != null) {
            token = jwtTokenProvider.createRefreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Email " + email + " not found");
        }

        return token;
    }
}
