package com.junseok.picrate.card.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junseok.picrate.common.dto.ApiResult;
import com.junseok.picrate.card.CardService;
import com.junseok.picrate.card.dto.CardResponse;
import com.junseok.picrate.rating.vo.RatingInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("api/v1")
@RestController
public class CardController {
    private final CardService cardService;

    public CardController(@Qualifier("cardServiceImpl") CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * 평가 저장
     * @param image  image
     * @param fields ratings
     * @return CardResponse DTO
     * @throws JsonProcessingException json parsing error
     */
    @PostMapping("/card")
    public ResponseEntity<ApiResult<CardResponse>> uploadCard(@RequestParam("image") MultipartFile image, @RequestParam("fields") String fields) throws JsonProcessingException {
        List<RatingInfo> ratings = new ObjectMapper().readValue(fields, new TypeReference<>() {});

        CardResponse cardResponse = cardService.uploadCard(image, ratings);
        
        return new ResponseEntity<>(
            ApiResult.succeed(cardResponse),
            HttpStatus.CREATED
        );
    }
}
