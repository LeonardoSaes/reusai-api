package com.br.reusai.api.domain.usecase.proposal.impl;

import com.br.reusai.api.domain.usecase.proposal.GetSentProposalsUsecase;
import com.br.reusai.api.host.controller.data.CompleteProposal;

import java.util.List;

public class GetSentProposalsUsecaseImpl implements GetSentProposalsUsecase {



    @Override
    public List<CompleteProposal> execute(String idUser) {
        return List.of();
    }
}
