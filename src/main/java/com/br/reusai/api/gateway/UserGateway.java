package com.br.reusai.api.gateway;

import com.br.reusai.api.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserGateway {
    String createUser(User user);
    User getUserByEmail(String email);
    UserDetails getUserDetailsByEmail(String email);
    UserDetails getUserById(String id);
    User getUserDomainById(String id);
}
