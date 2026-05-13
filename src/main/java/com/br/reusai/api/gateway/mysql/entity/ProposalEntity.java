package com.br.reusai.api.gateway.mysql.entity;

import com.br.reusai.api.utils.constants.StatusProposalEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proposal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // indica o id do usuario que fez a proposta
    @Column(nullable = false)
    private String idUserFrom;

    // indica o id do usuario que recebeu a proposta
    @Column(nullable = false)
    private String idUserTo;

    // indica o id do item que o usuario que fez a proposta quer receber
    @Column(nullable = false)
    private String idItemFrom;

    // indica o id do item que o usuario que recebeu a proposta tem para oferecer
    @Column(nullable = false)
    private String idItemTo;

    // indica o status da proposta, se foi aceita, recusada ou se ainda está pendente
    @Column(nullable = false)
    private StatusProposalEnum statusProposal;
}
