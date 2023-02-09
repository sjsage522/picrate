package com.junseok.picrate.util;

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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"spring.profiles.active=test"})
class FileUploadLocalUtilsTest {

    @Autowired
    private FileUploadLocalUtils fileUploadLocalUtils;

    @DisplayName("파일업로드 유틸 테스트 (로컬)")
    @Test
    void uploadFile() {
        byte[] imageBytes = getImageBytes();

        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("image", "test.jpg", "image/jpeg", imageBytes);

//        String homeDir = System.getProperty("user.home");
//        String result = fileUploadLocalUtils.uploadFile(mockMultipartFile, homeDir + "/picrate/tmp/data").orElse(null);
        String result = fileUploadLocalUtils.uploadFile(mockMultipartFile).orElse(null);

        assertThat(result).isNotNull();
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