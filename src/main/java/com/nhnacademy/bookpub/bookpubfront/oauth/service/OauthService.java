package com.nhnacademy.bookpub.bookpubfront.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.config.KeyConfig;
import com.nhnacademy.bookpub.bookpubfront.oauth.adaptor.OauthAdaptor;
import com.nhnacademy.bookpub.bookpubfront.oauth.dto.request.OauthMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.oauth.exception.TokenParsingException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * oauth service의 인터페이스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@RequiredArgsConstructor
@Slf4j
public abstract class OauthService {
    protected final OauthAdaptor oauthAdaptor;
    protected final ObjectMapper objectMapper;
    protected final KeyConfig keyConfig;

    /**
     * Url를 만들어주는 메소드.
     *
     * @return auth서버 연결 url.
     */
    public abstract String makeAuthUrl();

    /**
     * oauth에서 받은 code를 통해 accessToken을 가져오는 메소드.
     *
     * @param code 로그인을 통해 받은 code.
     * @return 인증된 사용자라는 증거인 accessToken.
     */
    public abstract String tokenRequestUrl(String code);

    /**
     * oauth에 사용자의 정보를 요청할 url을 빌드 하는 메소드.
     *
     * @return ㅕserInfoRequestUrl.
     */
    public abstract String userInfoRequestUrl();

    /**
     * {@inheritDoc}
     */
    public String getToken(String tokenRequestUrl) {
        ResponseEntity<String> tokenResponse = oauthAdaptor.getToken(tokenRequestUrl);

        Map token;

        try {
            token = objectMapper.readValue(tokenResponse.getBody(), Map.class);
        } catch (JsonProcessingException e) {
            throw new TokenParsingException();
        }

        return (String) token.get("access_token");
    }


    /**
     * 발급받은 토큰을 사용해 oauth 로그인 유저 정보를 가져오는 메소드.
     *
     * @param token code를 통해 반환받은 토큰.
     * @return 유저정보.
     */
    public Map<String, Object> getUserInfo(String token, String userInfoRequestUrl) {
        Map<String, Object> userInfo;

        try {
            ResponseEntity<String> kakaoUser = oauthAdaptor.getUser(userInfoRequestUrl, token);
            userInfo = objectMapper.readValue(kakaoUser.getBody(), Map.class);
        } catch (JsonProcessingException e) {
            throw new TokenParsingException();
        }

        return userInfo;
    }

    /**
     * map 형태를 dto형태로 변환시켜주는 메소드.
     *
     * @param userInfo github에서 반환받은 유저정보의 map 타입.
     * @return dto타입 유저정보.
     */
    public abstract OauthMemberRequestDto convertDto(Map<String, Object> userInfo);

    /**
     * 북펍의 회원인지 확인하는 메소드.
     *
     * @param oauthId 확인하고자 하는 아이디.
     * @return 회원이 맞는지 아닌지.
     */
    public boolean isOauthMember(String oauthId) {
        ResponseEntity<Boolean> response = oauthAdaptor.checkOauthMember(oauthId);

        return Boolean.TRUE.equals(response.getBody());
    }
}
