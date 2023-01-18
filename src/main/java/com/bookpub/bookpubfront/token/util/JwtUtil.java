package com.bookpub.bookpubfront.token.util;

import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 토큰 사용 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@ConfigurationProperties(prefix = "bookpub.jwt")
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtUtil {
    private String secret;
    public static final String JWT_SESSION = "JWT";
    private static final Base64.Decoder decoder = Base64.getUrlDecoder();

    /**
     * 토큰을 복호화해주는 메소드.
     *
     * @param jwt accessToken 정보.
     * @return 복호화 된 정보를 리턴해준다.
     */
    public static String decodeJwt(String jwt) {
        String jsonWebToken = jwt.split(" ")[1];
        String payload = jsonWebToken.split("\\.")[1];

        return new String(decoder.decode(payload));
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}