package com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception;

/**
 * 관리자 카테고리 페이지로 보내기 위한 예외입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public class GoToAdminCategoryException extends RuntimeException{
    private static final String MESSAGE = "카테고리 페이지로 이동!";
    public GoToAdminCategoryException() {
        super(MESSAGE);
    }
}
