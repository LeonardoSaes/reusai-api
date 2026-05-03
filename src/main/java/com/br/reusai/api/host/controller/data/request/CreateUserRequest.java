package com.br.reusai.api.host.controller.data.request;

import com.br.reusai.api.domain.model.Item;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateUserRequest(
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        String password,
        String photoUrl,
        @NotBlank
        String cep,
        List<Item>items
) {
}
