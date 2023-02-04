package com.junseok.picrate.common.exception;

import com.junseok.picrate.common.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {
    /**
     * 애플리케이션 비즈니스 예외들
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(
            final BusinessException ex) {
        final ErrorResponse response = ErrorResponse.of(ex.getMessage(), ex.getErrorCode());

        HttpStatus statusCode = HttpStatus.resolve(response.getStatus());
        if (statusCode == null) {
            statusCode = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(
            response,
            statusCode
        );
    }

    /**
     * 서버 에러
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(
            final Exception ex) {
        log.error("{}", ex.getMessage());
        return new ResponseEntity<>(
                ErrorResponse.of(ex.getMessage(), "SERVER_ERROR", 400),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
