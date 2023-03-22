package com.junseok.picrate.rating.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.junseok.picrate.rating.entity.Rating;
import com.junseok.picrate.rating.entity.RatingScore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingResponse {
    private Long id;

    private Double x;

    private Double y;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rate;

    private String label;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String rater;

    @Builder
    private RatingResponse(Long id, Double x, Double y, Integer rate, String label) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rate = rate;
        this.label = label;
    }

    public RatingResponse(Rating rating) {
        this.id = rating.getId();
        this.x = rating.getX();
        this.y = rating.getY();
        this.label = rating.getLabel();
    }

    public RatingResponse(RatingScore ratingScore) {
        Rating rating = ratingScore.getRating();
        this.id = ratingScore.getId();
        this.x = rating.getX();
        this.y = rating.getY();
        this.label = rating.getLabel();
        this.rate = ratingScore.getRate();
        this.rater = ratingScore.getRater();
    }
}
