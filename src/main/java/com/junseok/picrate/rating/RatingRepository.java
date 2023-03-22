package com.junseok.picrate.rating;

import java.util.*;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.junseok.picrate.rating.dto.RatingStatisticsResponse;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @EntityGraph(attributePaths = {"card"})
    List<Rating> findAllByCardId(Long cardId);

    Optional<Rating> findByIdAndCardId(Long id, Long cardId);

    @Query(value = "SELECT new com.junseok.picrate.rating.dto.RatingStatisticsResponse(r.rater, AVG(r.rate), SUM(r.rate)) FROM Rating r WHERE r.card.id = :card_id GROUP BY r.rater")
    List<RatingStatisticsResponse> findRatingStatisticsResponsesByCardId(@Param("card_id") Long cardId);
}
