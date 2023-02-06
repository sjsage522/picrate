package com.junseok.picrate.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junseok.picrate.common.exception.dto.ErrorResponse;

import lombok.Getter;

@Getter
public class ApiResult<T> {
    private final T data;
    
    @JsonProperty(value = "error")
    private final ErrorResponse errorResponse;

    private ApiResult(T data, ErrorResponse errorResponse) {
        this.data = data;
        this.errorResponse = errorResponse;
    }

    public static <T> ApiResult<T> succeed(T data) {
        return new ApiResult<T>(data, null);
    }

    public static <T> ApiResult<T> failed(ErrorResponse errorResponse) {
        return new ApiResult<T>(null, errorResponse);
    }
}
