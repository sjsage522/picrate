package com.junseok.picrate.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    FAILED_UPLOAD_IMAGE(400, "B001", "이미지 업로드를 실패했습니다."),
    NOT_ALLOWED_MIMETYPE(400, "B002", "허용되지 않은 파일 확장자 입니다."),
    NOT_FOUND_IMAGE(400, "B003", "존재하지 않는 이미지 파일 입니다."),
    NOT_FOUND_CARD(400, "B004", "존재하지 않는 작성카드 입니다."),
    NOT_FOUND_RATING(400, "B005", "존재하지 않는 평가 입니다."),

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
