package com.junseok.picrate.common.dto;

import lombok.Getter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class BaseTimeDto {
    @JsonProperty(value = "created_at")
    protected LocalDateTime createdAt;

    @JsonProperty(value = "modified_at")
    protected LocalDateTime modifiedAt;
}
