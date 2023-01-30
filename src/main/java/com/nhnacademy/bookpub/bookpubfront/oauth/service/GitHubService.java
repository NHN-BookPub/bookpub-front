package com.nhnacademy.bookpub.bookpubfront.oauth.service;

import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.BOOKPUB;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.CLIENT_ID;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.CLIENT_SECRET;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.GITHUB;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.HTTPS;
import static com.nhnacademy.bookpub.bookpubfront.oauth.Constance.REDIRECT_URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.config.KeyConfig;
import com.nhnacademy.bookpub.bookpubfront.oauth.adaptor.OauthAdaptor;
import com.nhnacademy.bookpub.bookpubfront.oauth.dto.request.OauthMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.oauth.exception.TokenParsingException;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * github의 api 명세에 맞춰 로직이 실행되는 클래스입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@ConfigurationProperties(prefix = "oauth.github")
@Slf4j
public class GitHubService extends OauthService {
    private String clientId;
    private String secret;
    private String redirectUri;

    public GitHubService(OauthAdaptor oauthAdaptor,
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
                .scheme(HTTPS)
                .host(GITHUB)
                .path("login/oauth/authorize")
                .queryParam(CLIENT_ID, clientId)
                .queryParam(REDIRECT_URL, redirectUri)
                .build().toUriString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken(String code) {
        String tokenRequestUrl = UriComponentsBuilder.newInstance()
                .scheme(HTTPS)
                .host(GITHUB)
                .path("login/oauth/access_token")
                .queryParam(CLIENT_ID, clientId)
                .queryParam(CLIENT_SECRET, secret)
                .queryParam("code", code)
                .queryParam(REDIRECT_URL, redirectUri)
                .build().toUriString();


        ResponseEntity<String> tokenResponse = oauthAdaptor.getToken(tokenRequestUrl);

        Map githubToken = null;

        try {
            githubToken = objectMapper.readValue(tokenResponse.getBody(), Map.class);
        } catch (JsonProcessingException e) {
            throw new TokenParsingException();
        }

        return (String) githubToken.get("access_token");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getUserInfo(String token) {
        String userRequestUrl = UriComponentsBuilder.newInstance()
                .scheme(HTTPS)
                .host("api." + GITHUB)
                .path("user")
                .build().toUriString();
        ResponseEntity<String> userResponse = oauthAdaptor.getUser(userRequestUrl, token);

        Map<String, Object> githubUser;
        try {
            githubUser = objectMapper.readValue(userResponse.getBody(), Map.class);
        } catch (JsonProcessingException e) {
            throw new TokenParsingException();
        }

        return githubUser;
    }

    /**
     * 반환받은 userResponse를 원하는 형태의 dto로 변환시켜주는 메소드.
     *
     * @param userResponse oauth서비스로부터 반환받은 정보.
     * @return 원하는 dto.
     */
    @Override
    public OauthMemberRequestDto convertDto(Map<String, Object> userResponse) {
        String email = (String) userResponse.get("email");
        int pwd = (int) userResponse.get("id");

        if (Objects.nonNull(email)) {
            return new OauthMemberRequestDto(email, String.valueOf(pwd));
        }

        String login = (String) userResponse.get("login");
        String convertEmail = login + BOOKPUB;

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
