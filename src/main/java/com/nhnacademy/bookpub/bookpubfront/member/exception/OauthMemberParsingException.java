package com.nhnacademy.bookpub.bookpubfront.member.exception;

/**
 * oauth 회원정보가 있는 멤버를 파싱하다 발생할 수 있는 에러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class OauthMemberParsingException extends RuntimeException {
    public static final String MESSAGE = "oauth 회원의 정보를 파싱할 수 없습니다.";

    public OauthMemberParsingException() {
        super(MESSAGE);
    }
}
