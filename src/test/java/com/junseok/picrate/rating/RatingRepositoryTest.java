package com.junseok.picrate.rating;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;

import com.junseok.picrate.card.Card;
import com.junseok.picrate.card.CardRepository;
import com.junseok.picrate.image.Image;
import com.junseok.picrate.image.ImageRepository;
import com.junseok.picrate.rating.dto.RatingStatisticsResponse;
import com.junseok.picrate.rating.vo.RatingInfo;

@DataJpaTest
@TestPropertySource(properties = {"spring.profiles.active=test"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RatingRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void testFindRatingStatisticsResponsesByCardId() {
        // given
        byte[] imageBytes = getImageBytes();
        
        MockMultipartFile mockMultipartFile =
            new MockMultipartFile("image", "test.jpg", "image/jpeg", imageBytes);

        String hashFileName = "TEST_HASH";

        Image newImage = Image.builder()
            .hashName(hashFileName)
            .orgName(mockMultipartFile.getOriginalFilename())
            .type(mockMultipartFile.getContentType())
            .size(mockMultipartFile.getSize())
            .build();
        Image savedImage = imageRepository.save(newImage);

        Card newCard = Card.builder()
            .image(savedImage)
            .build();
        Card savedCard = cardRepository.save(newCard);

        RatingInfo rating1 = RatingInfo.builder()
            .rate(4)
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
        List<RatingInfo> fields = List.of(rating1, rating2);
        
        List<Rating> ratingList = fields.stream().map(ratingInfo -> Rating.builder()
            .card(savedCard)
            .label(ratingInfo.getLabel())
            .x(ratingInfo.getX())
            .y(ratingInfo.getY())
            .rate(ratingInfo.getRate())
            .rater("tester")
            .build()).collect(Collectors.toList());
        ratingRepository.saveAll(ratingList);

        // when
        List<RatingStatisticsResponse> ratingStatisticsResponses = ratingRepository.findRatingStatisticsResponsesByCardId(savedCard.getId());

        // then
        Assertions.assertThat(ratingStatisticsResponses.get(0).getRater()).isEqualTo("tester");
        Assertions.assertThat(ratingStatisticsResponses.get(0).getAverage()).isEqualTo(3.0);
        Assertions.assertThat(ratingStatisticsResponses.get(0).getTotal()).isEqualTo(6);
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
