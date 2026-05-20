package com.br.reusai.api.domain.usecase.proposal;

import com.br.reusai.api.host.controller.data.CompleteProposal;

public interface CreateProposalUsecase {
    CompleteProposal execute(String idUserFrom, String idUserTo, String idItemFrom, String idItemTo);
}
