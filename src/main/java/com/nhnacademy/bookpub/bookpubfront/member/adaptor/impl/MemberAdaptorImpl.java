package com.nhnacademy.bookpub.bookpubfront.member.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.IdCheckRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.MemberAddressRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.ModifyMemberNameRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.ModifyMemberPasswordRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.ModifyMemberPhoneRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.NickCheckRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.OauthMemberCreateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberPasswordResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 멤버 어뎁터를 구현하기위한 구현 클래스입니다.
 *
 * @author : 유호철, 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private static final String TOKEN_API = "/token/members/";
    private static final String MEMBER_API = "/api/members/";
    private static final String AUTH_API = "/auth";


    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<SignupMemberResponseDto> signupRequest(
            SignupMemberRequestDto signupRequest) {

        HttpEntity<SignupMemberRequestDto> entity = new HttpEntity<>(signupRequest, makeHeader());

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/api/signup",
                HttpMethod.POST,
                entity,
                SignupMemberResponseDto.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<SignupMemberResponseDto> signupRequest(
            OauthMemberCreateRequestDto signupRequest) {

        HttpEntity<OauthMemberCreateRequestDto> entity =
                new HttpEntity<>(signupRequest, makeHeader());

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/api/oauth/signup",
                HttpMethod.POST,
                entity,
                SignupMemberResponseDto.class
        );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberNickNameChange(Long memberNo, String nickname) {
        HttpEntity<ModifyMemberNickNameRequestDto> httpentity =
                new HttpEntity<>(new ModifyMemberNickNameRequestDto(nickname), makeHeader());

        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/nickName",
                HttpMethod.PUT,
                httpentity,
                Void.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberEmailChange(Long memberNo, String email) {
        HttpEntity<ModifyMemberEmailRequestDto> httpEntity =
                new HttpEntity<>(new ModifyMemberEmailRequestDto(email), makeHeader());
        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/email",
                HttpMethod.PUT,
                httpEntity,
                Void.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public MemberDetailResponseDto requestMemberDetails(Long memberNo) {

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + MEMBER_API + "/" + memberNo,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                MemberDetailResponseDto.class
        ).getBody();
    }

    @Override
    public MemberDetailResponseDto requestTokenMemberDetails(Long memberNo) {

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + "/" + memberNo,
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
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + "/token/admin/members")
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
                GateWayConfig.getGatewayUrl() + "/token/admin/members/" + memberNo,
                HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberDelete(Long memberNo){
        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() +TOKEN_API + memberNo,
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
                GateWayConfig.getGatewayUrl() + "/token/admin/members/statistics",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                MemberStatisticsResponseDto.class
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MemberTierStatisticsResponseDto> requestMemberTierStatics() {

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/token/admin/tier/statistics",
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
                GateWayConfig.getGatewayUrl() + "/auth/login",
                HttpMethod.POST,
                entity,
                Void.class
        );
    }

    @Override
    public ResponseEntity<Void> tokenReIssueRequest(String accessToken) {
        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/auth/reissue",
                HttpMethod.POST,
                new HttpEntity<>(accessToken, makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> idDuplicateCheck(String id) {
        HttpEntity<IdCheckRequestDto> entity =
                new HttpEntity<>(new IdCheckRequestDto(id), makeHeader());

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/api/signup/idCheck",
                HttpMethod.POST,
                entity,
                Boolean.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> nickDuplicateCheck(String nickname) {
        HttpEntity<NickCheckRequestDto> entity =
                new HttpEntity<>(new NickCheckRequestDto(nickname), makeHeader());

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/api/signup/nickCheck",
                HttpMethod.POST,
                entity,
                Boolean.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberNameChange(Long memberNo, String name) {
        HttpEntity<ModifyMemberNameRequestDto> entity =
                new HttpEntity<>(new ModifyMemberNameRequestDto(name), makeHeader());

        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/name",
                HttpMethod.PUT,
                entity,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberPhoneChange(Long memberNo, String phone) {
        HttpEntity<ModifyMemberPhoneRequestDto> httpEntity =
                new HttpEntity<>(new ModifyMemberPhoneRequestDto(phone), makeHeader());

        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/phone",
                HttpMethod.PUT,
                httpEntity,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberPasswordResponseDto requestMemberPassword(Long memberNo) {
        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/password-check",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                MemberPasswordResponseDto.class).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberPasswordChange(Long memberNo, String password) {
        HttpEntity<ModifyMemberPasswordRequestDto> httpEntity =
                new HttpEntity<>(new ModifyMemberPasswordRequestDto(password), makeHeader());

        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/password",
                HttpMethod.PUT,
                httpEntity,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberDetailResponseDto requestAuthMemberInfo(String accessToken) {
        HttpHeaders headers = makeHeader();
        headers.add(JwtUtil.AUTH_HEADER, JwtUtil.TOKEN_TYPE + accessToken);

        return restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + "/token/auth",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                MemberDetailResponseDto.class).getBody();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberBaseAddressChange(Long memberNo, Long addressNo) {
        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/addresses/" + addressNo,
                HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberAddressAdd(Long memberNo, MemberAddressRequestDto requestDto) {
        HttpEntity<MemberAddressRequestDto> http = new HttpEntity<>(requestDto, makeHeader());
        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/addresses",
                HttpMethod.POST,
                http,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestMemberAddressDelete(Long memberNo, Long addressNo) {
        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/addresses/" + addressNo,
                HttpMethod.DELETE,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer requestMemberTier(Long memberNo) {
        ResponseEntity<Integer> response = restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + TOKEN_API + memberNo + "/tier",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                Integer.class
        );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout() {
        restTemplate.exchange(
                GateWayConfig.getGatewayUrl() + AUTH_API + "/logout",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<MemberResponseDto> requestMembersById(Pageable pageable, String id) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + "/token/admin/members/" + id + "/id")
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
    public PageResponse<MemberResponseDto> requestMembersByNickName(Pageable pageable, String nickName) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + "/token/admin/members/" + nickName + "/nick")
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
}
