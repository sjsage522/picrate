package com.junseok.picrate.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    FAILED_UPLOAD_IMAGE(400, "B001", "이미지 업로드를 실패했습니다."),

    TEST_CODE(400, "T001", "테스트 에러 코드");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
