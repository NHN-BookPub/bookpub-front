package com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception;

/**
 * 관리자 주문 페이지로 이동시키는 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public class GoToAdminOrderException extends RuntimeException {
    private static final String MESSAGE = "관리자 주문 페이지로 이동!";

    public GoToAdminOrderException() {
        super(MESSAGE);
    }
}
