package com.nhnacademy.bookpub.bookpubfront.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 400발생시 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final String MESSAGE = "잘못된 요청입니다.";
    public BadRequestException() {
        super(MESSAGE);
    }
}
