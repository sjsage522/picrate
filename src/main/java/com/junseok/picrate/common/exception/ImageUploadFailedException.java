package com.junseok.picrate.common.exception;

public class ImageUploadFailedException extends BusinessException {
    public ImageUploadFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
