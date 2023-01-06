package com.bookpub.bookpubfront.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * GateWay 와 연동하기위한 Config 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Configuration
public class GateWayConfig {
    @Value("${book-pub.gateway}")
    private String gatewayUrl;

    public String getGatewayUrl() {
        return gatewayUrl;
    }
}
