package com.nhnacademy.bookpub.bookpubfront.exception;

/**
 * 로그인이 필요할 때 발생하는 에러입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public class NotLoginException extends RuntimeException {
    public NotLoginException() {
        super();
    }
}
