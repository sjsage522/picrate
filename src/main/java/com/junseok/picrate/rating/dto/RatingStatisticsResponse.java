package com.junseok.picrate.rating.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingStatisticsResponse {
    private String rater;
    private Double average;
    private Long total;

    public RatingStatisticsResponse(String rater, Double average, Long total) {
        this.rater = rater;
        this.average = average;
        this.total = total;
    }
}
