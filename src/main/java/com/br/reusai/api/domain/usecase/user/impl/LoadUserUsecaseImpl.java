package com.br.reusai.api.domain.usecase.user.impl;

import com.br.reusai.api.domain.usecase.user.LoadUserUsecase;
import com.br.reusai.api.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadUserUsecaseImpl implements LoadUserUsecase {

    private final UserGateway userGateway;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userGateway.getUserDetailsByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email " + email + " not found");
        }
        return user;
    }
}
