package com.junseok.picrate.card;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>, CardRepositoryCustom {
    
    @EntityGraph(attributePaths = {"rating", "image"})
    Card getReferenceById(Long id);
}