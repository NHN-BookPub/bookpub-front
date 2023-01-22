package com.bookpub.bookpubfront.token.util;

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
    public static final String JWT_SESSION = "JWT";
    public static final String AUTH_HEADER = "Authorization";
}
