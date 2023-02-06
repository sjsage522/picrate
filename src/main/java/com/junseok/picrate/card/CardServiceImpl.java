package com.junseok.picrate.card;

import com.junseok.picrate.card.dto.CardResponse;
import com.junseok.picrate.common.exception.ErrorCode;
import com.junseok.picrate.common.exception.ImageUploadFailedException;
import com.junseok.picrate.image.Image;
import com.junseok.picrate.image.ImageRepository;
import com.junseok.picrate.image.dto.ImageResponse;
import com.junseok.picrate.rating.Rating;
import com.junseok.picrate.rating.RatingRepository;
import com.junseok.picrate.rating.dto.RatingResponse;
import com.junseok.picrate.rating.vo.RatingInfo;
import com.junseok.picrate.util.FileUploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private final FileUploadUtils fileUploadUtils;
    private final CardRepository cardRepository;
    private final ImageRepository imageRepository;
    private final RatingRepository ratingRepository;

    public CardServiceImpl(FileUploadUtils fileUploadUtils, CardRepository cardRepository, ImageRepository imageRepository, RatingRepository ratingRepository) {
        this.fileUploadUtils = fileUploadUtils;
        this.cardRepository = cardRepository;
        this.imageRepository = imageRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public CardResponse uploadCard(MultipartFile image, List<RatingInfo> fields) {
        final String hashFileName = fileUploadUtils.uploadFile(image).orElseThrow(
                () -> new ImageUploadFailedException(ErrorCode.FAILED_UPLOAD_IMAGE)
        );

        // Image DB insert
        final Image newImage = Image.builder()
                .hashName(hashFileName)
                .orgName(image.getOriginalFilename())
                .type(image.getContentType())
                .size(image.getSize())
                .build();
        final Image savedImage = imageRepository.save(newImage);

        // Card DB insert
        final Card newCard = Card.builder()
                .image(savedImage)
                .build();
        final Card savedCard = cardRepository.save(newCard);


        // Ratings DB insert
        List<Rating> ratingList = new ArrayList<>();
        fields.stream().map(ratingInfo -> Rating.builder()
                .card(savedCard)
                .label(ratingInfo.getLabel())
                .x(ratingInfo.getX())
                .y(ratingInfo.getY())
                .build()).forEachOrdered(ratingList::add);
        List<Rating> savedRatings = ratingRepository.saveAll(ratingList);
        List<RatingResponse> ratingResponseList = savedRatings.stream().map(RatingResponse::new)
                .collect(Collectors.toList());


        return CardResponse.builder()
                .id(newCard.getId())
                .imageResponse(new ImageResponse(savedImage))
                .ratingResponses(ratingResponseList)
                .createdAt(savedCard.getCreatedAt())
                .modifiedAt(savedCard.getModifiedAt())
                .build();
    }
}
