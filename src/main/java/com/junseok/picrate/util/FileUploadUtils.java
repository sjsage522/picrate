package com.junseok.picrate.util;


import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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

    Object uploadFile(MultipartFile file, String dirPath);

    Optional<String> uploadFile(MultipartFile file);
}
