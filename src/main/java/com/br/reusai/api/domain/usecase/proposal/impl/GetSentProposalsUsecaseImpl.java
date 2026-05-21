package com.br.reusai.api.domain.usecase.proposal.impl;

import com.br.reusai.api.domain.model.Proposal;
import com.br.reusai.api.domain.usecase.proposal.GetSentProposalsUsecase;
import com.br.reusai.api.gateway.ItemGateway;
import com.br.reusai.api.gateway.ProposalGateway;
import com.br.reusai.api.gateway.UserGateway;
import com.br.reusai.api.host.controller.data.CompleteProposal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.br.reusai.api.utils.constants.BuildCompleteProposals.getCompleteProposals;

@Component
@RequiredArgsConstructor
public class GetSentProposalsUsecaseImpl implements GetSentProposalsUsecase {

    private final ProposalGateway proposalGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;

    @Override
    public List<CompleteProposal> execute(String idUser) {
        List<Proposal> proposals = proposalGateway.getProposalsByIdUserFrom(idUser);

        return getCompleteProposals(proposals, userGateway, itemGateway);
    }
}
