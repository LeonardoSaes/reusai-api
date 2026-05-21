package com.br.reusai.api.domain.usecase.proposal.impl;

import com.br.reusai.api.domain.exception.BusinessException;
import com.br.reusai.api.domain.usecase.proposal.RejectReceivedProposalUsecase;
import com.br.reusai.api.gateway.ProposalGateway;
import com.br.reusai.api.utils.constants.StatusProposalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_CONTENT;

@Component
@RequiredArgsConstructor
public class RejectReceivedProposalUsecaseImpl implements RejectReceivedProposalUsecase {

    private final ProposalGateway proposalGateway;

    @Override
    public void execute(String idProposal) {
        var proposal = proposalGateway.getProposalById(idProposal);

        if(isNull(proposal)){
            throw new BusinessException(UNPROCESSABLE_CONTENT.value(), "Proposal not exist" );
        }

        proposalGateway.updateProposalStatus(idProposal, StatusProposalEnum.REJECTED);
    }
}
