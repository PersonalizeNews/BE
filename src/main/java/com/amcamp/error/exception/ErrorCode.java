package com.amcamp.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    AUTH_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "A1",  "AUTH INFO NOT FOUND"),
    TOKEN_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "T1", "FAIL_TO_VERIFY_TOKEN");
    // 에러 코드 추가하기

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
