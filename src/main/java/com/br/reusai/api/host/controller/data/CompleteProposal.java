package com.br.reusai.api.host.controller.data;

import com.br.reusai.api.utils.constants.StatusEnum;
import com.br.reusai.api.utils.constants.StatusProposalEnum;

public record CompleteProposal(
        String id,
        UserSnapshot userFrom,
        ItemSnapshot itemFrom,
        UserSnapshot userTo,
        ItemSnapshot itemTo,
        StatusProposalEnum statusProposal
) {

    public record UserSnapshot(
            String id,
            String username,
            String email,
            String photoUrl
    ) {
    }

    public record ItemSnapshot(
            String id,
            String title,
            String description,
            String category,
            String imageUrl,
            StatusEnum status,
            Boolean availableToChange
    ) {
    }
}
