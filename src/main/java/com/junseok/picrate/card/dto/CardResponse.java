package com.junseok.picrate.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junseok.picrate.common.dto.BaseTimeDto;
import com.junseok.picrate.image.dto.ImageResponse;
import com.junseok.picrate.rating.dto.RatingResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class CardResponse extends BaseTimeDto {
    private Long id;

    @JsonProperty(value = "image")
    private ImageResponse imageResponse;

    @JsonProperty(value = "ratings")
    private List<RatingResponse> ratingResponses;

    @Builder
    private CardResponse(Long id, ImageResponse imageResponse, List<RatingResponse> ratingResponses, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.imageResponse = imageResponse;
        this.ratingResponses = ratingResponses;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
