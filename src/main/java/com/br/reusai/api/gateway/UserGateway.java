package com.br.reusai.api.gateway;

import com.br.reusai.api.domain.model.User;

public interface UserGateway {
    String createUser(User user);
    User getUserByEmail(String email);
}
