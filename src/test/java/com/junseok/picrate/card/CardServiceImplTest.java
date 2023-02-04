package com.junseok.picrate.card;

import com.junseok.picrate.card.dto.CardResponse;
import com.junseok.picrate.rating.vo.RatingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"spring.profiles.active=test"})
class CardServiceImplTest {

    @Autowired
    private CardServiceImpl cardService;

    @DisplayName("[CardService] 업로드 테스트")
    @Test
    void uploadCard() {
        byte[] imageBytes = getImageBytes();

        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("image", "test.jpg", "image/jpeg", imageBytes);

        RatingInfo rating1 = RatingInfo.builder()
                .rate(8)
                .x(1.1)
                .y(1.2)
                .label("test label1")
                .build();
        RatingInfo rating2 = RatingInfo.builder()
                .rate(2)
                .x(3.33)
                .y(3.44)
                .label("test label2")
                .build();

        CardResponse cardResponse = cardService.uploadCard(mockMultipartFile, List.of(rating1, rating2));

        assertThat(cardResponse).isNotNull();
    }

    private byte[] getImageBytes() {
        // code to read an image file and return the bytes
        Resource resource = new ClassPathResource("test-image.jpg");
        try {
            return Files.readAllBytes(Paths.get(resource.getURI()));
        } catch (IOException e) {
            return null;
        }
    }
}