package com.br.reusai.api.domain.usecase.proposal.impl;

import com.br.reusai.api.domain.usecase.proposal.CreateProposalUsecase;
import com.br.reusai.api.gateway.ProposalGateway;
import com.br.reusai.api.host.controller.data.CompleteProposal;
import com.br.reusai.api.utils.constants.StatusProposalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProposalUsecaseImpl implements CreateProposalUsecase {

   private final ProposalGateway proposalGateway;

    @Override
    public CompleteProposal execute(String idUserFrom, String idUserTo, String idItemFrom, String idItemTo) {
        return proposalGateway.createProposal(idUserFrom, idUserTo, idItemFrom, idItemTo, StatusProposalEnum.CREATED);
    }
}
