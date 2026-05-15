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
    public TokenDTO execute(String username, String refreshToken) {
        if(isBlank(username) || isBlank(refreshToken)) {
            throw new UsernameNotFoundException("Invalid username or token");
        }

        var user = userGateway.getUserByUsername(username);
        TokenDTO token;
        if(user != null){
            token = jwtTokenProvider.createRefreshToken(refreshToken);
        } else{
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        return token;
    }
}
