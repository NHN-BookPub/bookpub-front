package com.bookpub.bookpubfront.utils;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 중복된 Header 를 반환 하는 유틸클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
public class Utils {
    private Utils() {
    }

    public static HttpHeaders makeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

}
