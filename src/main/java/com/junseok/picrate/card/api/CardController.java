package com.junseok.picrate.card.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junseok.picrate.common.dto.ApiResult;
import com.junseok.picrate.card.CardService;
import com.junseok.picrate.card.dto.CardResponse;
import com.junseok.picrate.rating.vo.RatingInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("api/v1")
@RestController
public class CardController {
    private final CardService cardService;

    public CardController(@Qualifier("cardServiceImpl") CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * 평가(카드) 저장
     * @param image  image
     * @param fields ratings
     * @return cardId
     * @throws IOException
     */
    @PostMapping("/card")
    public ResponseEntity<ApiResult<CardResponse>> uploadCard(@RequestParam("image") MultipartFile image, @RequestParam("fields") String fields) throws IOException {
        List<RatingInfo> ratings = new ObjectMapper().readValue(fields, new TypeReference<>() {});

        CardResponse cardResponse = cardService.uploadCard(image, ratings);

        return new ResponseEntity<>(
            ApiResult.succeed(
                CardResponse.builder()
                .id(cardResponse.getId())
                .build()
            ),
            HttpStatus.CREATED
        );
    }

    /**
     * 평가(카드) 조회
     * @param cardId id
     * @return CardResponse DTO
     */
    @GetMapping("/card/{cardId}")
    public ResponseEntity<ApiResult<CardResponse>> getCard(@PathVariable Long cardId) {
        CardResponse cardResponse = cardService.getCard(cardId);

        return new ResponseEntity<>(
            ApiResult.succeed(cardResponse),
            HttpStatus.OK
        );
    }
}
