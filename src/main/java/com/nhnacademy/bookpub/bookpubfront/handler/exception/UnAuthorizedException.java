package com.nhnacademy.bookpub.bookpubfront.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 401발생시 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
    private static final String MESSAGE = "권한이 없습니다.";

    public UnAuthorizedException() {
        super(MESSAGE);
    }
}
