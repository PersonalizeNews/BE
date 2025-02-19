package com.amcamp.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    AUTH_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH INFO NOT FOUND"),
    TOKEN_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "FAIL_TO_VERIFY_TOKEN");

    private final HttpStatus httpStatus;
    private final String message;
}
