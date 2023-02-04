package com.junseok.picrate.card.dto;

import com.junseok.picrate.image.dto.ImageResponse;
import com.junseok.picrate.rating.dto.RatingResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CardResponse {
    private Long id;

    private ImageResponse imageResponse;

    private List<RatingResponse> ratingResponses;

    @Builder
    private CardResponse(Long id, ImageResponse imageResponse, List<RatingResponse> ratingResponses) {
        this.id = id;
        this.imageResponse = imageResponse;
        this.ratingResponses = ratingResponses;
    }
}
