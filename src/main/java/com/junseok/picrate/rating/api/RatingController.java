package com.junseok.picrate.rating.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import com.junseok.picrate.common.dto.ApiResult;
import com.junseok.picrate.rating.RatingService;
import com.junseok.picrate.rating.dto.RatingApplyRequest;
import com.junseok.picrate.rating.dto.RatingResponse;
import com.junseok.picrate.rating.dto.RatingStatisticsResponse;

@RequestMapping("api/v1")
@RestController
public class RatingController {
    private final RatingService ratingService;

    public RatingController(@Qualifier("ratingServiceImpl") RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/ratings/card/{cardId}")
    public ResponseEntity<ApiResult<List<RatingResponse>>> applyRatings(@PathVariable Long cardId, @Valid @RequestBody RatingApplyRequest request) {
        List<RatingResponse> ratingResponses = ratingService.applyRatings(cardId, request.getRater(), request.getRatingApplyInfos());
        return new ResponseEntity<>(
            ApiResult.succeed(
                ratingResponses
            ),
            HttpStatus.CREATED
        );
    }

    @GetMapping("/ratings/card/{cardId}")
    public ResponseEntity<ApiResult<List<RatingStatisticsResponse>>> getRatingStatistics(@PathVariable Long cardId) {
        List<RatingStatisticsResponse> ratingStatisticsResponse = ratingService.findRatingStatisticsResponsesByCardId(cardId);

        return new ResponseEntity<>(
            ApiResult.succeed(
                ratingStatisticsResponse
            ),
            HttpStatus.OK
        );
    }
}
