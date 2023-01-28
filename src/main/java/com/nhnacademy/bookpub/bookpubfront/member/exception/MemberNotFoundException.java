package com.nhnacademy.bookpub.bookpubfront.member.exception;

/**
 * 유저를 찾을 수 없을 때 발생하는 에러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class MemberNotFoundException extends RuntimeException {
    public static final String MESSAGE = "존재하지 않는 아이디입니다";

    public MemberNotFoundException() {
        super(MESSAGE);
    }

}
