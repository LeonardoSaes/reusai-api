package com.br.reusai.api.utils.constants;

import com.br.reusai.api.domain.model.Proposal;
import com.br.reusai.api.gateway.ItemGateway;
import com.br.reusai.api.gateway.UserGateway;
import com.br.reusai.api.host.controller.data.CompleteProposal;

import java.util.ArrayList;
import java.util.List;

public class BuildCompleteProposals {

    public static List<CompleteProposal> getCompleteProposals(List<Proposal> proposals, UserGateway userGateway, ItemGateway itemGateway) {
        List<CompleteProposal> filledCompleteProposals = new ArrayList<>();

        for (Proposal proposal : proposals) {
            var userFrom = userGateway.getUserDomainById(proposal.getIdUserFrom());
            var userTo = userGateway.getUserDomainById(proposal.getIdUserTo());
            var itemFrom = itemGateway.getItemById(proposal.getIdItemFrom());
            var itemTo = itemGateway.getItemById(proposal.getIdItemTo());

            CompleteProposal completeProposal = new CompleteProposal(
                    new CompleteProposal.UserSnapshot(userFrom.getId(), userFrom.getUsername(), userFrom.getEmail(), userFrom.getPhotoUrl()),
                    new CompleteProposal.ItemSnapshot(itemFrom.getId(), itemFrom.getTitle(), itemFrom.getDescription(), itemFrom.getCategory(), itemFrom.getImageUrl(), itemFrom.getStatus(), itemFrom.getAvailableToChange()),
                    new CompleteProposal.UserSnapshot(userTo.getId(), userTo.getUsername(), userTo.getEmail(), userTo.getPhotoUrl()),
                    new CompleteProposal.ItemSnapshot(itemTo.getId(), itemTo.getTitle(), itemTo.getDescription(), itemTo.getCategory(), itemTo.getImageUrl(), itemTo.getStatus(), itemTo.getAvailableToChange()),
                    proposal.getStatusProposal()
            );

            filledCompleteProposals.add(completeProposal);
        }

        return filledCompleteProposals;
    }
}
