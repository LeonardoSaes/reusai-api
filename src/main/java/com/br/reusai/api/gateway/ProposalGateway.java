package com.br.reusai.api.gateway;

import com.br.reusai.api.domain.model.Proposal;
import com.br.reusai.api.host.controller.data.CompleteProposal;
import com.br.reusai.api.utils.constants.StatusProposalEnum;

import java.util.List;

public interface ProposalGateway {
    CompleteProposal createProposal(String idUserFrom, String idUserTo, String idItemFrom, String idItemTo, StatusProposalEnum status);
    List<Proposal> getProposalsByIdUserFrom(String userId);
    List<Proposal> getProposalsByIdUserTo(String userId);
    Proposal getProposalById(String id);
    void updateProposalStatus(String id, StatusProposalEnum status);
}
