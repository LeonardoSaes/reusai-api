package com.br.reusai.api.host.controller.data.response;

public record UserProfileResponse(
        String id,
        String username,
        String email,
        String photoUrl
) {
}
