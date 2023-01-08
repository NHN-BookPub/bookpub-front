package com.bookpub.bookpubfront.member.adaptor;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.member.dto.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.SignupMemberResponseDto;
import lombok.RequiredArgsConstructor;
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
public class MemberAdaptor {
    private final RestTemplate restTemplate;
    private final GateWayConfig gatewayUrl;

    public ResponseEntity<SignupMemberRequestDto> signupRequest(SignupMemberResponseDto signupRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SignupMemberResponseDto> entity = new HttpEntity<>(signupRequest, headers);

        return restTemplate.exchange(
                gatewayUrl.getGatewayUrl() + "/api/signup",
                HttpMethod.POST,
                entity,
                SignupMemberRequestDto.class
        );
    }
}
