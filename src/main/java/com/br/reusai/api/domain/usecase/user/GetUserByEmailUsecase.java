package com.br.reusai.api.domain.usecase.user;

import com.br.reusai.api.domain.model.User;

public interface GetUserByEmailUsecase {
    User execute(String email);
}
