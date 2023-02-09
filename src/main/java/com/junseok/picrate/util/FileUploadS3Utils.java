package com.junseok.picrate.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.List;

/**
 * 사용자가 업로드한 파일을 서버(S3)에 업로드하기 위한 util class
 */
public class FileUploadS3Utils implements FileUploadUtils {
    @Override
    public Optional<String> uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public byte[] getFileBytes(String hash) {
        return null;
    }

    @Override
    public List<String> getAllowExtensions() {
        return null;
    }
}
