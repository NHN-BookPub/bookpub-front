package com.nhnacademy.bookpub.bookpubfront.utils;

import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * cookie 관련된 유틸 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class CookieUtil {

    private CookieUtil() {
    }

    /**
     * accessCookie 를 찾는 메서드 입니다.
     *
     * @return 쿠키의 검색값을 가져옵니다.
     */
    public static Cookie findCookie(String cookieName) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        if (Objects.isNull(request.getCookies())) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    /**
     * 쿠키를 만들어주는 메소드 입니다.
     *
     * @param response 통신 응답객체.
     * @param key      쿠키의 이름.
     * @param value    쿠키의 값.
     */
    public static void makeCookie(HttpServletResponse response,
                                  String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 최근본 상품을 저장할 쿠키 생성 메섣,.
     *
     * @param response HttpServletResponse
     * @param key      쿠키 key
     * @param value    쿠키 value
     */
    public static void makeRecentViewCookie(HttpServletResponse response,
                                            String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(86400);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 쿠키를 삭제하는 메소드 입니다.
     *
     * @param response 통신 응답객체.
     * @param key      쿠키이름.
     */
    public static void deleteCookie(HttpServletResponse response, String key) {
        Cookie cookie = findCookie(key);
        Objects.requireNonNull(cookie).setMaxAge(0);
        response.addCookie(cookie);
    }


}
