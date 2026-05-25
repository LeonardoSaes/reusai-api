package com.br.reusai.api.gateway.mysql.repository;

import com.br.reusai.api.gateway.mysql.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    ItemEntity findItemByTitle(String title);
    ItemEntity findItemById(String id);
    List<ItemEntity> findItemByCategory(String category);
    List<ItemEntity> findItemByUserId(String userId);
    List<ItemEntity> findItemByUserIdNotOrderByCreatedAtAsc(String userId);
}
