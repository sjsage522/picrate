package com.junseok.picrate.util;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.tika.Tika;

public interface FileUploadUtils {
    String HASH_ALGORITHM = "SHA-1";

    /**
     * common function
     * 파일내용을 hashing 하여 유니크한 파일이름을 생성함
     * @param fileContents 파일 내용
     * @return hashing filename (stored filename)
     */
    default Optional<String> hashingFileName(String fileContents) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            return Optional.empty();
        }
        md.update(fileContents.getBytes(StandardCharsets.UTF_8));

        byte[] byteData = md.digest();
        StringBuilder buffer = new StringBuilder();
        for (byte b : byteData) {
            buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }

        return Optional.of(buffer.toString());
    }

    /**
     * common function
     * 허용된 확장자들로 파일의 mimeType 체크
     * @param bytes file bytes
     * @return file mimeType
     * @throws IOException
     */
    default Optional<String> getMimeType(byte[] bytes) {
        List<String> allowExtensions = this.getAllowExtensions();
        try {
            String mimeType = new Tika().detect(new ByteArrayInputStream(bytes));

            Boolean isValid = allowExtensions.stream()
                .anyMatch(ext -> ext.equalsIgnoreCase(mimeType));

            return isValid ? Optional.of(mimeType) : Optional.empty();
        } catch(IOException e) {
            return Optional.empty();
        }
    }

    Optional<String> uploadFile(MultipartFile file);

    byte[] getFileBytes(String hash);

    List<String> getAllowExtensions();
}
