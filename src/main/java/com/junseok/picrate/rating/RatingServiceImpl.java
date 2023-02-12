package com.junseok.picrate.rating;

import com.junseok.picrate.common.exception.ErrorCode;
import com.junseok.picrate.common.exception.NotFoundRatingException;
import com.junseok.picrate.rating.dto.RatingResponse;
import com.junseok.picrate.rating.dto.RatingApplyRequest.RatingApplyInfo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Transactional
    public List<RatingResponse> applyRatings(Long cardId, String rater, List<RatingApplyInfo> ratingApplyInfos) {
        List<Rating> ratings = ratingApplyInfos.stream().map(
            ratingApplyInfo -> {
                Rating findRating = ratingRepository.findByIdAndCardId(ratingApplyInfo.getId(), cardId).orElseThrow(() -> new NotFoundRatingException(ErrorCode.NOT_FOUND_RATING));
                findRating.updateRate(ratingApplyInfo.getRate());
                findRating.setRater(rater);
                return findRating;
            }
        ).collect(Collectors.toList());

        return ratings.stream().map(
            RatingResponse::new
        ).collect(Collectors.toList());
    }
}
