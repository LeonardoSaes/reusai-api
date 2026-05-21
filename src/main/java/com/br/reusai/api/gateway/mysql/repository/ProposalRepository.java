package com.br.reusai.api.gateway.mysql.repository;

import com.br.reusai.api.gateway.mysql.entity.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, String> {
    List<ProposalEntity> findProposalsByIdUserFrom(String userId);
    List<ProposalEntity> findProposalsByIdUserTo(String userId);
}
