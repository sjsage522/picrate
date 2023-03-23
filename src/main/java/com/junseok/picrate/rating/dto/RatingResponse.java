package com.junseok.picrate.rating.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junseok.picrate.rating.entity.Rating;

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

    private String label;

    @Builder
    private RatingResponse(Long id, Double x, Double y, String label) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public RatingResponse(Rating rating) {
        this.id = rating.getId();
        this.x = rating.getX();
        this.y = rating.getY();
        this.label = rating.getLabel();
    }
}
