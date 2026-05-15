package com.br.reusai.api.domain.usecase.auth;

import com.br.reusai.api.host.controller.data.request.security.AccountCredencialsDTO;
import com.br.reusai.api.host.controller.data.response.security.TokenDTO;

public interface SigninUsecase {

    TokenDTO execute(AccountCredencialsDTO credencials);
}
