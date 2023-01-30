package com.nhnacademy.bookpub.bookpubfront.token.exception;

/**
 * 토큰이 발행되지 않을경우 발생하는 exception.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class TokenNotIssuedException extends RuntimeException {
    public static final String MESSAGE = "토큰이 정상적으로 발급되지 않았습니다.";

    public TokenNotIssuedException() {
        super(MESSAGE);
    }
}
