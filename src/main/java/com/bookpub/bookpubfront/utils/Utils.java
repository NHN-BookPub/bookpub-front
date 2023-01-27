package com.bookpub.bookpubfront.utils;

import com.bookpub.bookpubfront.token.util.JwtUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 중복된 Header 를 반환 하는 유틸클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 */
public class Utils {
    private Utils() {
    }

    /**
     * 헤더생성에 대한 중복을 방지한 메서드입니다.
     *
     * @return http 헤더가 반환됩니다.
     */
    public static HttpHeaders makeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    public static Cookie findJwtCookie() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        if(Objects.isNull(request.getCookies())){
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtUtil.JWT_SESSION))
                .findAny()
                .orElse(null);
    }

    /**
     * 에러를 체크하기 위한 메서드입니다.
     * 400, 500번대 에러를 거릅니다.
     *
     * @param response ResponseEntity 받습니다.
     * @return 에러가 없으면 그대로 반환합니다.
     * @param <T> 지네릭 타입입니다.
     */
    public static <T> ResponseEntity<T> checkError(ResponseEntity response) {
        HttpStatus status = response.getStatusCode();

        if (status.is4xxClientError()) {
            throw new RuntimeException();
        }

        if (status.is5xxServerError()) {
            throw new RuntimeException();
        }

        return response;
    }

}
