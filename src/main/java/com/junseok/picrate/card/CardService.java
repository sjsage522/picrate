package com.junseok.picrate.card;

import com.junseok.picrate.card.dto.CardResponse;
import com.junseok.picrate.rating.vo.RatingInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CardService {

    CardResponse uploadCard(MultipartFile image, List<RatingInfo> fields);
}
