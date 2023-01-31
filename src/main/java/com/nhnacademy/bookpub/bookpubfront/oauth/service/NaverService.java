package com.nhnacademy.bookpub.bookpubfront.oauth.service;

import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.BOOKPUB_EMAIL;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.CLIENT_ID;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.CLIENT_SECRET;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.HTTPS;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.NAVER;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.NAVER_EMAIL;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.REDIRECT_URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.config.KeyConfig;
import com.nhnacademy.bookpub.bookpubfront.oauth.adaptor.OauthAdaptor;
import com.nhnacademy.bookpub.bookpubfront.oauth.dto.request.OauthMemberRequestDto;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * naver의 api 명세에 맞춰 로직이 실행되는 클래스입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@ConfigurationProperties(prefix = "oauth.naver")
@Slf4j
public class NaverService extends OauthService {
    private String clientId;
    private String secret;
    private String redirectUri;

    public NaverService(OauthAdaptor oauthAdaptor,
                        ObjectMapper objectMapper,
                        KeyConfig keyConfig) {
        super(oauthAdaptor, objectMapper, keyConfig);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeAuthUrl() {
        return UriComponentsBuilder.newInstance()
                .scheme(HTTPS.getValue())
                .host(NAVER.getValue())
                .path("oauth2.0/authorize")
                .queryParam(REDIRECT_URI.getValue(), redirectUri)
                .queryParam("response_type", "code")
                .queryParam(CLIENT_ID.getValue(), clientId)
                .build().toUriString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String tokenRequestUrl(String code) {
        return UriComponentsBuilder.newInstance()
                .scheme(HTTPS.getValue())
                .host(NAVER.getValue())
                .path("oauth2.0/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("code", code)
                .queryParam(CLIENT_ID.getValue(), clientId)
                .queryParam(CLIENT_SECRET.getValue(), secret)
                .build().toUriString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String userInfoRequestUrl() {
        return UriComponentsBuilder.newInstance()
                .scheme(HTTPS.getValue())
                .host("openapi.naver.com")
                .path("v1/nid/me")
                .build().toUriString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OauthMemberRequestDto convertDto(Map<String, Object> userInfo) {
        Map<String, String> naverAccount
                = (Map) userInfo.get("response");

        String email = naverAccount.get("email");
        String pwd = naverAccount.get("id");

        if (Objects.nonNull(email)) {
            email = email.split("@")[0] + NAVER_EMAIL.getValue();
            return new OauthMemberRequestDto(email, pwd);
        }

        String login = UUID.randomUUID().toString().replace("-", "");
        String convertEmail = login + BOOKPUB_EMAIL.getValue();

        return new OauthMemberRequestDto(convertEmail, String.valueOf(pwd));
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = keyConfig.keyStore(clientId);
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = keyConfig.keyStore(secret);
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = keyConfig.keyStore(redirectUri);
    }
}
