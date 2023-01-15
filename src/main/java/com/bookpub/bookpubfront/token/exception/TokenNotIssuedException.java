package com.bookpub.bookpubfront.token.exception;

/**
 * Some description here
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
