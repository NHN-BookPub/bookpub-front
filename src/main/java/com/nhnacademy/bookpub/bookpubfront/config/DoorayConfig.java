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

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate, getHookUrl());
    }

    public String getHookUrl() {
        return hookUrl;
    }

    public void setHookUrl(String hookUrl) {
        this.hookUrl = hookUrl;
    }


}
