package com.nhnacademy.bookpub.bookpubfront.oauth;

/**
 * oauth에 이용되는 상수들을 정리해둔 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public enum Constance {
    HTTPS("https"),
    GITHUB("github.com"),
    KAKAO("kauth.kakao.com"),
    NAVER("nid.naver.com"),
    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret"),
    REDIRECT_URI("redirect_uri"),
    BOOKPUB_EMAIL("@bookpub.com"),
    GITHUB_EMAIL("@github.com"),
    NAVER_EMAIL("@naver.com"),
    KAKAO_EMAIL("@kakao.com");

    private final String value;


    Constance(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
