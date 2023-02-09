package com.junseok.picrate.common.exception;

public class NotAllowedMimeTypeException extends BusinessException{
    public NotAllowedMimeTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
