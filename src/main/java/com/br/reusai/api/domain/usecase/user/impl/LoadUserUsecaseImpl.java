package com.br.reusai.api.domain.usecase.user.impl;

import com.br.reusai.api.domain.usecase.user.LoadUserUsecase;
import com.br.reusai.api.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class LoadUserUsecaseImpl implements LoadUserUsecase {

    private final UserGateway userGateway;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userGateway.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user;
    }
}
