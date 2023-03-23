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

    private String label;

    @JsonProperty(value = "rating_average")
    private Double ratingAverage;

    @JsonProperty(value = "rating_sum")
    private Long ratingSum;

    public RatingStatisticsResponse(Long ratingId, String label, Double ratingAverage, Long ratingSum) {
        this.ratingId = ratingId;
        this.label = label;
        this.ratingAverage = ratingAverage;
        this.ratingSum = ratingSum;
    }
}
