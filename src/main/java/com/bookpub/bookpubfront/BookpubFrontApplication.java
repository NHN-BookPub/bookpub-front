package com.bookpub.bookpubfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BookpubFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookpubFrontApplication.class, args);
    }

}
