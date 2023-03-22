package com.junseok.picrate.rating.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingStatisticsResponse {
    @JsonProperty(value = "rating_id")
    private Long ratingId;

    @JsonProperty(value = "rating_average")
    private Double ratingAverage;

    @JsonProperty(value = "rating_sum")
    private Long ratingSum;

    public RatingStatisticsResponse(Long ratingId, Double ratingAverage, Long ratingSum) {
        this.ratingId = ratingId;
        this.ratingAverage = ratingAverage;
        this.ratingSum = ratingSum;
    }
}
