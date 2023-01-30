package com.nhnacademy.bookpub.bookpubfront.oauth.exception;

/**
 * 토큰 파싱이 실패했을 때 발생하는 에러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class TokenParsingException extends RuntimeException {
    public static final String MESSAGE = "토큰을 파싱하는데 실패하였습니다";

    public TokenParsingException() {
        super(MESSAGE);
    }
}
