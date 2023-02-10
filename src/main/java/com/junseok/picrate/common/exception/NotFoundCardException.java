package com.junseok.picrate.common.exception;

public class NotFoundCardException extends BusinessException{
    public NotFoundCardException(ErrorCode errorCode) {
        super(errorCode);
    }    
}
