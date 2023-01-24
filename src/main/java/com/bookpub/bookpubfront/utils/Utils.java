package com.bookpub.bookpubfront.utils;

import com.bookpub.bookpubfront.token.util.JwtUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 중복된 메소드를 관리해주는 클래스입니다.
 *
 * @author : 유호철, 임태원
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

    /**
     * accessCookie 를 찾는 메서드 입니다.
     *
     * @return 쿠키의 검색값을 가져옵니다.
     */
    public static Cookie findJwtCookie() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        if (Objects.isNull(request.getCookies())) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtUtil.JWT_COOKIE))
                .findAny()
                .orElse(null);
    }

}
