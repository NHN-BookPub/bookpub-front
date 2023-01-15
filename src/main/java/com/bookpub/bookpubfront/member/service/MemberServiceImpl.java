package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.token.exception.TokenNotIssuedException;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 멤버 도메인의 서비스 메소드를 구현하는 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberAdaptor memberAdaptor;

    /**
     * {@inheritDoc}
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public SignupMemberResponseDto signup(SignupMemberRequestDto signupMemberRequestDto) {
        String originPwd = signupMemberRequestDto.getPwd();
        String encodePwd = passwordEncoder.encode(originPwd);

        signupMemberRequestDto.setEncodePwd(encodePwd);

        ResponseEntity<SignupMemberResponseDto> exchange
                = memberAdaptor.signupRequest(signupMemberRequestDto);

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void login(LoginMemberRequestDto loginMemberRequestDto, HttpSession session) {
        ResponseEntity<Void> jwtResponse = memberAdaptor.loginRequest(loginMemberRequestDto);

        String accessToken = Objects.requireNonNull(jwtResponse.getHeaders().get("Authorization")).get(0);

        if(Objects.isNull(accessToken)){
            throw new TokenNotIssuedException();
        }

        session.setAttribute(JwtUtil.JWT_SESSION, accessToken);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberNickName(Long memberNo, ModifyMemberNickNameRequestDto dto) {
        memberAdaptor.requestMemberNickNameChange(memberNo, dto);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberEmail(Long memberNo, ModifyMemberEmailRequestDto dto) {
        memberAdaptor.requestMemberEmailChange(memberNo, dto);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public MemberDetailResponseDto getMember(Long memberNo){
        return memberAdaptor.requestMemberDetails(memberNo);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<MemberResponseDto> getMembers(Pageable pageable){
        return memberAdaptor.requestMembers(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void memberBlock(Long memberNo){
        memberAdaptor.requestMemberBlock(memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberStatisticsResponseDto memberStatistics(){
        return memberAdaptor.requestMemberStatics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MemberTierStatisticsResponseDto> memberTierStatistics(){
        return memberAdaptor.requestMemberTierStatics();
    }
}
