package com.junseok.picrate.common.exception;

public class NotFoundImageException extends BusinessException {
    public NotFoundImageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
