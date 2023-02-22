package com.junseok.picrate.util;

import com.junseok.picrate.common.property.AppProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.*;

/**
 * 사용자가 업로드한 파일을 서버(로컬)에 업로드하기 위한 util class
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class FileUploadLocalUtils implements FileUploadUtils {
    private final AppProperty appProperty;

    private final List<String> allowExtensions = List.of(
        "image/jpeg", "image/png", "image/jpg"
    );

    @Override
    public Optional<String> uploadFile(MultipartFile file) {
        return this.uploadFile(file, appProperty.getFileStorePath());
    }

    @Override
    public byte[] getFileBytes(String hashName) {
        return this.getFileBytes(hashName, appProperty.getFileStorePath());
    }

    @Override
    public List<String> getAllowExtensions() {
        return this.allowExtensions;
    }

    private byte[] getFileBytes(String hashName, String dirPath) {
        String fileFullPath = this.getFileFullPath(hashName, dirPath);

        Path path = Paths.get(fileFullPath);

        try {
            return Files.readAllBytes(path);
        } catch(IOException e) {
            log.error("{}", e.getMessage());
            return new byte[0];
        }
    }

    private Optional<String> uploadFile(MultipartFile file, String dirPath) {
        try {
            final String hashName = hashingFileName(new String(file.getBytes())).orElseThrow(NoSuchAlgorithmException::new);
            String fileDirPath = this.getFileDirPath(hashName, dirPath);
            String fileFullPath = this.getFileFullPath(hashName, dirPath);
            File dir = new File(fileDirPath);
            File uploadFile = new File(fileFullPath);

            boolean mkdirIsSuccess = Boolean.TRUE;
            if (!dir.exists()) {
                mkdirIsSuccess = dir.mkdirs();
            }

            if (!mkdirIsSuccess) {
                log.error("{}", "Failed to make dir path.");
                return Optional.empty();
            }

            try {
                file.transferTo(uploadFile);
            } catch (IOException e) {
                log.error("{}", e.getMessage());
                return Optional.empty();
            }

            return Optional.of(hashName);
        } catch (NoSuchAlgorithmException | IOException e) {
            return Optional.empty();
        }
    }

    private String getFileDirPath(String hashName, String dirPath) {
        return dirPath + "/" + hashName.substring(0, 2);
     }
 
     private String getFileFullPath(String hashName, String dirPath) {
         return this.getFileDirPath(hashName, dirPath) + "/" + hashName;
     }
}
