package com.junseok.picrate.rating;

import java.util.List;

import com.junseok.picrate.rating.dto.RatingResponse;
import com.junseok.picrate.rating.dto.RatingStatisticsResponse;
import com.junseok.picrate.rating.dto.RatingApplyRequest.RatingApplyInfo;

public interface RatingService {
    List<RatingResponse> applyRatings(Long cardId, String rater, List<RatingApplyInfo> ratingApplyInfos);

    List<RatingStatisticsResponse> findRatingStatisticsResponsesByCardId(Long cardId);
}
