package com.br.reusai.api.gateway.converter;

import com.br.reusai.api.domain.model.Proposal;
import com.br.reusai.api.gateway.mysql.entity.ProposalEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ProposalGatewayConverter {
    List<Proposal> toDomain(List<ProposalEntity> proposalEntity);
}
