package com.nhnacademy.bookpub.bookpubfront.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 502발생시 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends RuntimeException {
    public BadGatewayException() {
        super();
    }
}
