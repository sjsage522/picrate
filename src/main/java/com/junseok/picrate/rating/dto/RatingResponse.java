package com.junseok.picrate.rating.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.junseok.picrate.rating.Rating;
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
        this.rate = rating.getRate();
        this.label = rating.getLabel();
        this.rater = rating.getRater();
    }
}
