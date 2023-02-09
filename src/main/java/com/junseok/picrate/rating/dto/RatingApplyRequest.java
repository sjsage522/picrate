package com.junseok.picrate.rating.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RatingApplyRequest { 
    @NotBlank
    private String name;

    @Valid @NotNull
    @JsonProperty(value = "fields")
    private List<RatingApplyInfo> ratingApplyInfos;

    @Getter
    @ToString
    public static class RatingApplyInfo {
        @NotBlank
        private Double x;

        @NotBlank
        private Double y;

        @NotBlank
        private Integer rate;

        @NotBlank
        private String label;

        protected RatingApplyInfo() {}
    }

    protected RatingApplyRequest() {}
}
