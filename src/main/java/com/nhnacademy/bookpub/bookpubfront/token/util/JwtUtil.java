package com.nhnacademy.bookpub.bookpubfront.token.util;

import javax.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 토큰 사용 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Component
@Slf4j
public class JwtUtil {
    public static final String JWT_COOKIE = "bookpub_accessToken";
    public static final String AUTH_HEADER = "Authorization";
    public static final String EXP_HEADER = "X-Expire";
    public static final String TOKEN_TYPE = "Bearer ";
    public static final Long MILL_SEC = 1000L;
    public static final Integer EXPIRE_TIME = 7200;

    private JwtUtil() {
    }

    /**
     * jwt를 보관용 cookie를 만드는 메소드.
     *
     * @param accessToken 유저 인증 토큰.
     * @param expireTime  만료시간.
     * @return Cookie.
     */
    public static Cookie makeJwtCookie(String accessToken, Long expireTime) {
        String tokenInfo = accessToken + "." + expireTime;

        Cookie cookie = new Cookie(JwtUtil.JWT_COOKIE, tokenInfo);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(EXPIRE_TIME);
        return cookie;
    }
}
