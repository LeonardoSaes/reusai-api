package com.br.reusai.api.host.controller.data.request;

import jakarta.validation.constraints.NotBlank;

public record CreateProposalRequest(
        @NotBlank
        String idUserFrom,
        @NotBlank
        String idUserTo,
        @NotBlank
        String idItemFrom,
        @NotBlank
        String idItemTo
) {
}
