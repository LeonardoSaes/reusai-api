package com.br.reusai.api.domain.usecase.auth;

import com.br.reusai.api.host.controller.data.response.security.TokenDTO;

public interface RefreshTokenUsecase {
    TokenDTO execute(String email, String refreshToken);
}
