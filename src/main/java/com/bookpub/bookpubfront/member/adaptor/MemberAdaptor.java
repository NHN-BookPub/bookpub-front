package com.bookpub.bookpubfront.member.adaptor;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 멤버의 서버간 데이터 송신 및 수신을 담당하는 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberAdaptor {
    private final RestTemplate restTemplate;
    private final GateWayConfig gatewayUrl;

    public ResponseEntity<SignupMemberResponseDto> signupRequest(
            SignupMemberRequestDto signupRequest) {
        HttpEntity<SignupMemberRequestDto> entity = new HttpEntity<>(signupRequest, makeHeader());

        return restTemplate.exchange(
                gatewayUrl.getGatewayUrl() + "/api/signup",
                HttpMethod.POST,
                entity,
                SignupMemberResponseDto.class
        );
    }

    public ResponseEntity<Void> loginRequest(
            LoginMemberRequestDto loginRequest) {
        HttpEntity<LoginMemberRequestDto> entity = new HttpEntity<>(loginRequest, makeHeader());

        return restTemplate.exchange(
                gatewayUrl.getGatewayUrl() + "/auth/login",
                HttpMethod.POST,
                entity,
                Void.class
        );
    }

    public HttpHeaders makeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
