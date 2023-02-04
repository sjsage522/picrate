package com.junseok.picrate.util;

import com.junseok.picrate.common.property.AppProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * 사용자가 업로드한 파일을 서버(로컬)에 업로드하기 위한 util class
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class FileUploadLocalUtils implements FileUploadUtils {
    private final AppProperty appProperty;

    @Override
    public Optional<String> uploadFile(MultipartFile file, String dirPath) {
        String appEnv = appProperty.getEnvironment();
        if (!"prod".equals(appEnv)) {
            String homeDir = System.getProperty("user.home");
            dirPath = homeDir + "/picrate/tmp/data";
        }

        try {
            final String hashName = hashingFileName(new String(file.getBytes())).orElseThrow(NoSuchAlgorithmException::new);
            String fileDirPath = dirPath + "/" + hashName.substring(0, 2);
            String fileFullPath = fileDirPath + "/" + hashName;
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

    @Override
    public Optional<String> uploadFile(MultipartFile file) {
        return this.uploadFile(file, "/data/files/picrate");
    }
}
