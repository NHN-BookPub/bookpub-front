package com.nhnacademy.bookpub.bookpubfront.config;

import com.nhn.dooray.client.DoorayHookSender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 두레이 훅을 이용하기위한 config 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "dooray")
@RequiredArgsConstructor
public class DoorayConfig {
    private final RestTemplate restTemplate;
    private String hookUrl;
    private String wishlistHookUrl;

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate, getHookUrl());
    }

    @Bean
    public DoorayHookSender doorayHookWishlistAlarmSender() {
        return new DoorayHookSender(restTemplate, getWishlistHookUrl());
    }

    public String getHookUrl() {
        return hookUrl;
    }

    public void setHookUrl(String hookUrl) {
        this.hookUrl = hookUrl;
    }

    public String getWishlistHookUrl() {
        return wishlistHookUrl;
    }

    public void setWishlistHookUrl(String wishlistHookUrl) {
        this.wishlistHookUrl = wishlistHookUrl;
    }
}
