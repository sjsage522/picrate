package com.junseok.picrate.rating;

import java.util.*;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.junseok.picrate.rating.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @EntityGraph(attributePaths = {"card"})
    List<Rating> findAllByCardId(Long cardId);

    @EntityGraph(attributePaths = {"card"})
    Optional<Rating> findByIdAndCardId(Long id, Long cardId);
}
