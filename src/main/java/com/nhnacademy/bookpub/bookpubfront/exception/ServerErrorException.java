package com.nhnacademy.bookpub.bookpubfront.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 500발생시 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException {
    private static final String MESSAGE = "알 수 없는 에러입니다. 이후에 다시 시도해주세요.";

    public ServerErrorException() {
        super(MESSAGE);
    }
}
