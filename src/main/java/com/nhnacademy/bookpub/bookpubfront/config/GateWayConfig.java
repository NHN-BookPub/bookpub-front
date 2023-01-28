package com.nhnacademy.bookpub.bookpubfront.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * GateWay 와 연동하기위한 Config 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "book-pub")
public class GateWayConfig {
    private static String gateway;

    public static String getGatewayUrl() {
        return gateway;
    }

    public void setGateway(String gateway) {
        GateWayConfig.gateway = gateway;
    }
}
