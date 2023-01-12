package com.bookpub.bookpubfront.member.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import com.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 멤버 어뎁터를 구현하기위한 구현 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private final GateWayConfig gateWayConfig;

    private static final String MEMBER_API = "/api/members";

    @Override
    public ResponseEntity<SignupMemberResponseDto> signupRequest(SignupMemberRequestDto signupRequest) {

        HttpEntity<SignupMemberRequestDto> entity = new HttpEntity<>(signupRequest, Utils.makeHeader());

        return restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + "/api/signup",
                HttpMethod.POST,
                entity,
                SignupMemberResponseDto.class
        );
    }

    @Override
    public void requestMemberNickNameChange(Long memberNo, ModifyMemberNickNameRequestDto requestDto) {

        restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + MEMBER_API + memberNo + "/nickName",
                HttpMethod.PUT,
                new HttpEntity<>(requestDto, Utils.makeHeader()),
                Void.class);
    }

    @Override
    public void requestMemberEmailChange(Long memberNo, ModifyMemberEmailRequestDto requestDto) {

        restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + MEMBER_API + memberNo + "/email",
                HttpMethod.PUT,
                new HttpEntity<>(requestDto, Utils.makeHeader()),
                Void.class);
    }

    @Override
    public MemberDetailResponseDto requestMemberDetails(Long memberNo) {

        return restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + MEMBER_API + memberNo,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                MemberDetailResponseDto.class
        ).getBody();
    }

    @Override
    public PageResponse<MemberResponseDto> requestMembers(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(gateWayConfig.getGatewayUrl() + "/api/admin/members")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<PageResponse<MemberResponseDto>>() {
                }
        ).getBody();
    }

    @Override
    public void requestMemberBlock(Long memberNo) {
        restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + "/api/admin/members/" + memberNo,
                HttpMethod.PUT,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );
    }
}
