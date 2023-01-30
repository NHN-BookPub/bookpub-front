package com.nhnacademy.bookpub.bookpubfront.oauth.adaptor;

import org.springframework.http.ResponseEntity;

/**
 * oauth를 제공해주는 도메인과 통신하게해주는 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface OauthAdaptor {
    /**
     * oauth 서비스를 제공해주는 도메인과 통신하여 토큰을 가져오는 메소드.
     *
     * @param url 도메인 url.
     * @return 토큰응답값.
     */
    ResponseEntity<String> getToken(String url);

    /**
     * oauth 서비스를 제공해주는 도메인과 통신하여 유저정보를 가져오는 메소드.
     *
     * @param url   도메인 url
     * @param token 인가받은 토큰.
     * @return 유저의 정보.
     */
    ResponseEntity<String> getUser(String url, String token);

    /**
     * oauth 계정으로 가입한 회원인지 확인하는 메소드.
     *
     * @param oauthId oauth 서버에서 보내준 회원의 아이디.
     * @return 상태코드를 통해 확인.
     */
    ResponseEntity<Boolean> checkOauthMember(String oauthId);
}
