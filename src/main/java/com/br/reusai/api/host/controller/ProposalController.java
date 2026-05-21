package com.br.reusai.api.host.controller;

import com.br.reusai.api.domain.usecase.proposal.CreateProposalUsecase;
import com.br.reusai.api.domain.usecase.proposal.GetReceivedProposalsUsecase;
import com.br.reusai.api.domain.usecase.proposal.GetSentProposalsUsecase;
import com.br.reusai.api.host.controller.data.CompleteProposal;
import com.br.reusai.api.host.controller.data.request.CreateProposalRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proposal")
public class ProposalController {

    private final CreateProposalUsecase createProposalUsecase;
    private final GetSentProposalsUsecase getSentProposalsUsecase;
    private final GetReceivedProposalsUsecase getReceivedProposalsUsecase;

    @PostMapping()
    public ResponseEntity<CompleteProposal> createProposal(@RequestBody @Valid CreateProposalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                createProposalUsecase.execute(request.idUserFrom(), request.idUserTo(), request.idItemFrom(), request.idItemTo()));
    }

    @GetMapping("/sent/{idUserFrom}")
    public ResponseEntity<List<CompleteProposal>> getSentProposals(@PathVariable String idUserFrom) {
        return ResponseEntity.status(HttpStatus.OK).body(getSentProposalsUsecase.execute(idUserFrom));
    }

    @GetMapping("/received/{idUserTo}")
    public ResponseEntity<List<CompleteProposal>> getReceivedProposals(@PathVariable String idUserTo){
        return ResponseEntity.status(HttpStatus.OK).body(getReceivedProposalsUsecase.execute(idUserTo));
    }

}

