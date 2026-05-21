package com.br.reusai.api.domain.usecase.proposal;

import com.br.reusai.api.host.controller.data.CompleteProposal;

import java.util.List;

public interface GetReceivedProposalsUsecase {
    List<CompleteProposal> execute(String idUser);
}
