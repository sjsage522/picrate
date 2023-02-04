package com.junseok.picrate.common.exception.dto;

import com.junseok.picrate.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;
    private final String code;
    private final int status;

    private ErrorResponse(String message, String code, int status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public static ErrorResponse from(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getCode(), errorCode.getStatus());
    }

    public static ErrorResponse of(String message, String code, int status) {
        return new ErrorResponse(message, code, status);
    }

    public static ErrorResponse of(String message, ErrorCode errorCode) {
        return new ErrorResponse(message, errorCode.getCode(), errorCode.getStatus());
    }
}
