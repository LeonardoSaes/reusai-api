package com.br.reusai.api.gateway.mysql.impl;

import com.br.reusai.api.domain.model.Proposal;
import com.br.reusai.api.gateway.ProposalGateway;
import com.br.reusai.api.gateway.converter.ProposalGatewayConverter;
import com.br.reusai.api.gateway.mysql.entity.ProposalEntity;
import com.br.reusai.api.gateway.mysql.repository.ItemRepository;
import com.br.reusai.api.gateway.mysql.repository.ProposalRepository;
import com.br.reusai.api.gateway.mysql.repository.UserRepository;
import com.br.reusai.api.host.controller.data.CompleteProposal;
import com.br.reusai.api.utils.constants.StatusProposalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProposalGatewayImpl implements ProposalGateway {

    private final ProposalRepository proposalRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ProposalGatewayConverter proposalGatewayConverter;

    @Override
    public CompleteProposal createProposal(String idUserFrom, String idUserTo, String idItemFrom, String idItemTo, StatusProposalEnum statusProposal) {

        var userFrom = userRepository.findUserById(idUserFrom);
        if (userFrom == null) {
            throw new IllegalArgumentException("UserFrom not found: " + idUserFrom);
        }

        var itemFrom = itemRepository.findItemById(idItemFrom);
        if (itemFrom == null) {
            throw new IllegalArgumentException("ItemFrom not found: " + idItemFrom);
        }

        var userTo = userRepository.findUserById(idUserTo);
        if (userTo == null) {
            throw new IllegalArgumentException("UserTo not found: " + idUserTo);
        }

        var itemTo = itemRepository.findItemById(idItemTo);
        if (itemTo == null) {
            throw new IllegalArgumentException("ItemTo not found: " + idItemTo);
        }

        Proposal newProposal = new Proposal(
                null,
                idUserFrom,
                idUserTo,
                idItemFrom,
                idItemTo,
                statusProposal
        );

        ProposalEntity entityToSave = new ProposalEntity(
                null,
                newProposal.getIdUserFrom(),
                newProposal.getIdUserTo(),
                newProposal.getIdItemFrom(),
                newProposal.getIdItemTo(),
                newProposal.getStatusProposal()
        );

        var saved = proposalRepository.save(entityToSave);
        newProposal.setId(saved.getId());

        return new CompleteProposal(
                saved.getId(),
                new CompleteProposal.UserSnapshot(userFrom.getId(), userFrom.getUsername(), userFrom.getEmail(), userFrom.getPhotoUrl()),
                new CompleteProposal.ItemSnapshot(
                        itemFrom.getId(),
                        itemFrom.getTitle(),
                        itemFrom.getDescription(),
                        itemFrom.getCategory(),
                        itemFrom.getImageUrl(),
                        itemFrom.getStatus(),
                        itemFrom.getAvailableToChange()
                ),
                new CompleteProposal.UserSnapshot(userTo.getId(), userTo.getUsername(), userTo.getEmail(), userTo.getPhotoUrl()),
                new CompleteProposal.ItemSnapshot(
                        itemTo.getId(),
                        itemTo.getTitle(),
                        itemTo.getDescription(),
                        itemTo.getCategory(),
                        itemTo.getImageUrl(),
                        itemTo.getStatus(),
                        itemTo.getAvailableToChange()
                ),
                saved.getStatusProposal()
        );
    }

    @Override
    public List<Proposal> getProposalsByIdUserFrom(String userId) {
        return proposalGatewayConverter.toListDomain(proposalRepository.findProposalsByIdUserFrom(userId));
    }

    @Override
    public List<Proposal> getProposalsByIdUserTo(String userId) {
        return proposalGatewayConverter.toListDomain(proposalRepository.findProposalsByIdUserTo(userId));
    }

    @Override
    public Proposal getProposalById(String id) {
        return proposalGatewayConverter.toDomain(proposalRepository.findById(id).orElse(null));
    }

    @Override
    public void updateProposalStatus(String id, StatusProposalEnum status) {
        proposalRepository.findById(id).ifPresent(proposalEntity -> {
            proposalEntity.setStatusProposal(status);
            proposalRepository.save(proposalEntity);
        });
    }
}
