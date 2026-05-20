package com.br.reusai.api.gateway;

import com.br.reusai.api.domain.model.Proposal;
import com.br.reusai.api.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserGateway {
    String createUser(User user);
    User getUserByEmail(String email);
    UserDetails getUserDetailsByEmail(String email);
    User getUserById(String id);
    List<Proposal> getProposalsByIdUserFrom(String userId);
}
