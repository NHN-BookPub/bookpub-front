package com.nhnacademy.bookpub.bookpubfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * bookpub springboot starter.
 */
@SpringBootApplication
@EnableRedisHttpSession
@ConfigurationPropertiesScan
public class BookpubFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookpubFrontApplication.class, args);
    }

}
