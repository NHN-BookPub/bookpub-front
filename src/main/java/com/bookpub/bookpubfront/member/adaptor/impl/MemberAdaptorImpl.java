package com.bookpub.bookpubfront.member.adaptor.impl;

import static com.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
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


    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<SignupMemberResponseDto> signupRequest(SignupMemberRequestDto signupRequest) {

        HttpEntity<SignupMemberRequestDto> entity = new HttpEntity<>(signupRequest, makeHeader());

        return restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + "/api/signup",
                HttpMethod.POST,
                entity,
                SignupMemberResponseDto.class
        );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberNickNameChange(Long memberNo, ModifyMemberNickNameRequestDto requestDto) {

        restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + MEMBER_API + memberNo + "/nickName",
                HttpMethod.PUT,
                new HttpEntity<>(requestDto, makeHeader()),
                Void.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberEmailChange(Long memberNo, ModifyMemberEmailRequestDto requestDto) {

        restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + MEMBER_API + memberNo + "/email",
                HttpMethod.PUT,
                new HttpEntity<>(requestDto, makeHeader()),
                Void.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public MemberDetailResponseDto requestMemberDetails(Long memberNo) {

        return restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + MEMBER_API + memberNo,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                MemberDetailResponseDto.class
        ).getBody();
    }


    /**
     * {@inheritDoc}
     */
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
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<PageResponse<MemberResponseDto>>() {
                }
        ).getBody();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberBlock(Long memberNo) {
        restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + "/api/admin/members/" + memberNo,
                HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberStatisticsResponseDto requestMemberStatics() {

        return restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + "/api/admin/members/statistics",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                MemberStatisticsResponseDto.class
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MemberTierStatisticsResponseDto> requestMemberTierStatics(){

        return restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + "/api/admin/tier/statistics",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<List<MemberTierStatisticsResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> loginRequest(LoginMemberRequestDto loginRequest) {
        HttpEntity<LoginMemberRequestDto> entity = new HttpEntity<>(loginRequest, makeHeader());

        return restTemplate.exchange(
                gateWayConfig.getGatewayUrl() + "/auth/login",
                HttpMethod.POST,
                entity,
                Void.class
        );
    }
}
