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

    /** 회원가입을 위해 shop 서버와 통신하는 메소드.
     *
     * @param signupRequest 회원가입을 위한 멤버정보가 들어있는 요청 dto.
     * @return shop 서버에서 전달해준 회원가입 승낙 정보가 들어있는 응답 dto.
     */
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

    /** 로그인을 위해 auth 서버와 통신하는 메소드.
     *
     * @param loginRequest 로그인을 위한 멤버정보가 들어있는 요청 dto.
     * @return auth 서버에서 생성된 토큰을 헤더에 담아 응답해준다.
     */
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

    /** 요청헤더를 만드는 과정을 담아둔 메소드.
     *  추후 util로 빼도 될듯 함.
     *
     * @return 만들어진 header를 반환한다.
     */
    public HttpHeaders makeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
