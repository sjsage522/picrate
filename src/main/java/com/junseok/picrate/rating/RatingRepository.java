package com.junseok.picrate.rating;

import java.util.*;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @EntityGraph(attributePaths = {"card"})
    List<Rating> findAllByCardId(Long cardId);

    Optional<Rating> findByIdAndCardId(Long id, Long cardId);
}
