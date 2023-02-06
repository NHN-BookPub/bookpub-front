package com.nhnacademy.bookpub.bookpubfront.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 404발생시 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final String MESSAGE = "찾을 수 없습니다.";

    public NotFoundException() {
        super(MESSAGE);
    }
}
