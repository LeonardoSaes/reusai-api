package com.br.reusai.api.gateway;

import com.br.reusai.api.host.controller.data.CompleteProposal;
import com.br.reusai.api.utils.constants.StatusProposalEnum;

public interface ProposalGateway {
    CompleteProposal createProposal(String idUserFrom, String idUserTo, String idItemFrom, String idItemTo, StatusProposalEnum status);
}
