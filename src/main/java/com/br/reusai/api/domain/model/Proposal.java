package com.br.reusai.api.domain.model;

import com.br.reusai.api.utils.constants.StatusProposalEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Proposal {

    private String id;
    private String idUserFrom;
    private String idUserTo;
    private String idItemFrom;
    private String idItemTo;
    private StatusProposalEnum statusProposal;
}
