package com.nhnacademy.bookpub.bookpubfront.config;

import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.TossProviderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 토스 결제 서비스를 이용하기 위한 config.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "toss")
@RequiredArgsConstructor
public class TossConfig {
    private final KeyConfig keyConfig;
    private String clientId;
    private String secret;
    private String successUrl;
    private String failUrl;

    public TossProviderDto makeTossProvider() {
        return new TossProviderDto(clientId, secret, successUrl, failUrl);
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

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = keyConfig.keyStore(successUrl);
    }

    public String getFailUrl() {
        return failUrl;
    }

    public void setFailUrl(String failUrl) {
        this.failUrl = keyConfig.keyStore(failUrl);
    }
}
