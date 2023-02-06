package com.junseok.picrate.rating.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RatingInfo {
    private Integer rate;

    private String label;

    private Double x;

    private Double y;

    @Builder
    private RatingInfo(Integer rate, String label, Double x, Double y) {
        this.rate = rate;
        this.label = label;
        this.x = x;
        this.y = y;
    }
}
