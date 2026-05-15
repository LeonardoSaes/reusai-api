package com.br.reusai.api.domain.usecase.auth.impl;

import com.br.reusai.api.domain.config.JwtTokenProvider;
import com.br.reusai.api.domain.usecase.auth.SigninUsecase;
import com.br.reusai.api.gateway.UserGateway;
import com.br.reusai.api.gateway.converter.UserDetailsConverter;
import com.br.reusai.api.host.controller.data.request.security.AccountCredencialsDTO;
import com.br.reusai.api.host.controller.data.response.security.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SigninUsecaseImpl implements SigninUsecase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserGateway userGateway;
    private final UserDetailsConverter userDetailsConverter;

    @Override
    public TokenDTO execute(AccountCredencialsDTO credencials) {
        if(credencials == null || credencials.getUsername() == null || credencials.getPassword() == null){
            throw new IllegalArgumentException("Username and password must be provided");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credencials.getUsername(),
                            credencials.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        var user = userGateway.getUserByUsername(credencials.getUsername());
        if(user == null){
            throw new UsernameNotFoundException("Username " + credencials.getUsername() + " not found");
        }

        var userEntity = userDetailsConverter.toUserEntity(user);
        return jwtTokenProvider.createAccessToken(credencials.getUsername(), userEntity.getRoles());
    }
}
