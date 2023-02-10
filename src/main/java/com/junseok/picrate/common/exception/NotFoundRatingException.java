package com.junseok.picrate.common.exception;

public class NotFoundRatingException extends BusinessException{
    public NotFoundRatingException(ErrorCode errorCode) {
        super(errorCode);
    }
}
