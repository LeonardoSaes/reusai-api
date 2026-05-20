package com.br.reusai.api.host.controller.data;

import com.br.reusai.api.utils.constants.StatusProposalEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateProposalDto(

        @NotBlank
        String idUserFrom,
        @NotBlank
        String idUserTo,
        @NotBlank
        String idItemFrom,
        @NotBlank
        String idItemTo,
        @NotEmpty
        StatusProposalEnum statusProposal
) {
}
