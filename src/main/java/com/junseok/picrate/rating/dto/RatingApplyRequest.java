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
    @JsonProperty(value = "name")
    private String rater;

    @Valid @NotNull
    @JsonProperty(value = "fields")
    private List<RatingApplyInfo> ratingApplyInfos;

    @Getter
    @ToString
    public static class RatingApplyInfo {
        @NotNull
        private Long id;

        @NotNull
        private Integer rate;

        protected RatingApplyInfo() {}
    }

    protected RatingApplyRequest() {}
}
