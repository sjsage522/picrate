package com.junseok.picrate.rating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.junseok.picrate.rating.dto.RatingStatisticsResponse;
import com.junseok.picrate.rating.entity.RatingScore;

public interface RatingScoreRepository extends JpaRepository<RatingScore, Long> {
    @Query(value = "SELECT new com.junseok.picrate.rating.dto.RatingStatisticsResponse(rs.rating.id, AVG(rs.rate), SUM(rs.rate)) FROM RatingScore rs WHERE rs.card.id = :card_id GROUP BY rs.rating.id")
    List<RatingStatisticsResponse> findRatingStatisticsResponsesByCardId(@Param("card_id") Long cardId);
}
