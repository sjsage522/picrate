package com.junseok.picrate.card;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CardRepository extends JpaRepository<Card, Long>, CardRepositoryCustom {
    
    @EntityGraph(attributePaths = {"rating", "image"})
    Card getReferenceById(Long id);
}