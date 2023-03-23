package com.junseok.picrate.rating;

import com.junseok.picrate.common.exception.ErrorCode;
import com.junseok.picrate.common.exception.NotFoundRatingException;
import com.junseok.picrate.rating.dto.RatingScoreResponse;
import com.junseok.picrate.rating.dto.RatingStatisticsResponse;
import com.junseok.picrate.rating.dto.RatingApplyRequest.RatingApplyInfo;
import com.junseok.picrate.rating.entity.Rating;
import com.junseok.picrate.rating.entity.RatingScore;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingScoreRepository ratingScoreRepository;

    @Transactional
    public List<RatingScoreResponse> applyRatings(Long cardId, String rater, List<RatingApplyInfo> ratingApplyInfos) {
        List<RatingScore> ratingScores = ratingApplyInfos.stream().map(
            ratingApplyInfo -> {
                Rating findRating = ratingRepository.findByIdAndCardId(ratingApplyInfo.getId(), cardId).orElseThrow(() -> new NotFoundRatingException(ErrorCode.NOT_FOUND_RATING));
                RatingScore ratingScore = RatingScore 
                    .builder()
                    .rating(findRating)
                    .card(findRating.getCard())
                    .rate(ratingApplyInfo.getRate())
                    .rater(rater)
                    .build();
                return ratingScore;
            }
        ).collect(Collectors.toList());

        List<RatingScore> savedRatingScores = ratingScoreRepository.saveAll(ratingScores);

        return savedRatingScores.stream().map(
            RatingScoreResponse::new
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RatingStatisticsResponse> findRatingStatisticsResponsesByCardId(Long cardId) {
        return ratingScoreRepository.findRatingStatisticsResponsesByCardId(cardId);
    }
}
