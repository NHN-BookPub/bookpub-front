package com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception;

/**
 * 메인페이지로 보내기 위한 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public class GoToMainException extends RuntimeException {
    private static final String MESSAGE = "메인페이지로 이동!";
    public GoToMainException() {
        super(MESSAGE);
    }
}
