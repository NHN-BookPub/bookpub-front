package com.nhnacademy.bookpub.bookpubfront.oauth.adaptor;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * adaptor의 구현체입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class OauthAdaptorImpl implements OauthAdaptor {
    private final RestTemplate restTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> getToken(String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(makeHeader()),
                String.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> getUser(String url, String token) {
        HttpHeaders headers = makeHeader();
        headers.add(JwtUtil.AUTH_HEADER, JwtUtil.TOKEN_TYPE + token);

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> checkOauthMember(String oauthId) {
        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/api/oauth/" + oauthId,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                Boolean.class
        );
    }
}
