package com.br.reusai.api.gateway.mysql.repository;

import com.br.reusai.api.gateway.mysql.entity.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, String> {
}
