package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.proposal.CreateProposalUsecase;
import com.br.reusai.api.host.controller.data.CompleteProposal;
import com.br.reusai.api.host.controller.data.request.CreateProposalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proposal")
public class ProposalController {

    private final CreateProposalUsecase createProposalUsecase;

    @PostMapping()
    public ResponseEntity<CompleteProposal> createProposal(@RequestBody CreateProposalRequest request) {
         return ResponseEntity.status(HttpStatus.CREATED).body(
        createProposalUsecase.execute(request.idUserFrom(), request.idUserTo(), request.idItemFrom(), request.idItemTo()));
    }
}
