package com.nhnacademy.bookpub.bookpubfront.oauth.exception;

/**
 * 지원하지 않는 oauth 서비스일 때 발생하는 에러입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class NotAllowedOauthServiceException extends RuntimeException {
    public static final String MESSAGE = "지원하지 않는 oauth 서비스입니다";

    public NotAllowedOauthServiceException() {
        super(MESSAGE);
    }
}
