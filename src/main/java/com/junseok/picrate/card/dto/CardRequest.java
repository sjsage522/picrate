package com.junseok.picrate.card.dto;

import com.junseok.picrate.rating.vo.RatingInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class CardRequest {
    private MultipartFile image;

    private List<RatingInfo> fields;
}


