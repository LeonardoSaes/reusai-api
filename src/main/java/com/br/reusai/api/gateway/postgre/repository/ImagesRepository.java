package com.br.reusai.api.gateway.postgre.repository;

import com.br.reusai.api.gateway.postgre.entity.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity, Integer> {
}


