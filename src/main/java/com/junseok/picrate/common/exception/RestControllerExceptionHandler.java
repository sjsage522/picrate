package com.junseok.picrate.common.exception;

import com.junseok.picrate.common.dto.ApiResult;
import com.junseok.picrate.common.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {
    /**
     * 404 NOT FOUND
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResult<ErrorResponse>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        final ErrorResponse response = ErrorResponse.from(ErrorCode.NOT_FOUND);

        return new ResponseEntity<>(
            ApiResult.failed(response),
            HttpStatus.NOT_FOUND
        );
    }

    /**
     * 애플리케이션 비즈니스 예외들
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResult<ErrorResponse>> handleBusinessException(
            final BusinessException ex) {
        final ErrorResponse response = ErrorResponse.of(ex.getMessage(), ex.getErrorCode());

        HttpStatus statusCode = HttpStatus.resolve(response.getStatus());
        if (statusCode == null) {
            statusCode = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(
            ApiResult.failed(response),
            statusCode
        );
    }

    /**
     * 서버 에러
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResult<ErrorResponse>> handleException(
            final Exception ex) {
        log.error("{}", ex.getMessage());
        return new ResponseEntity<>(
                ApiResult.failed(ErrorResponse.of(ex.getMessage(), "SERVER_ERROR", 400)),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
