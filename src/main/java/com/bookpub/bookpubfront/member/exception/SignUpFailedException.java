package com.bookpub.bookpubfront.member.exception;

/**
 * 회원가입 실패 exception.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class SignUpFailedException extends RuntimeException {
    public SignUpFailedException(String id) {
        super(id + "로 회원가입하는데 실패했습니다.");
    }
}
