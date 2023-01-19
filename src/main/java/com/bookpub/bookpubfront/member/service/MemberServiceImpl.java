package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberPasswordResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.token.exception.TokenNotIssuedException;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final RedisTemplate<String, String> redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final MemberAdaptor memberAdaptor;

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

        if (Objects.isNull(accessToken)) {
            throw new TokenNotIssuedException();
        }

        session.setAttribute(JwtUtil.JWT_SESSION, accessToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            redisTemplate.opsForHash().delete((String) authentication.getPrincipal(), authentication.getCredentials());
            session.invalidate();
            SecurityContextHolder.clearContext();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberNickName(Long memberNo, String nickname) {
        memberAdaptor.requestMemberNickNameChange(memberNo, nickname);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberEmail(Long memberNo, String email) {
        memberAdaptor.requestMemberEmailChange(memberNo, email);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public MemberDetailResponseDto getMember(Long memberNo) {
        return memberAdaptor.requestMemberDetails(memberNo);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<MemberResponseDto> getMembers(Pageable pageable) {
        return memberAdaptor.requestMembers(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void memberBlock(Long memberNo) {
        memberAdaptor.requestMemberBlock(memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberStatisticsResponseDto memberStatistics() {
        return memberAdaptor.requestMemberStatics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MemberTierStatisticsResponseDto> memberTierStatistics() {
        return memberAdaptor.requestMemberTierStatics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean idDuplicateCheck(String id) {
        return Boolean.TRUE.equals(memberAdaptor.idDuplicateCheck(id).getBody());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nickDuplicateCheck(String nickname) {
        return Boolean.TRUE.equals(memberAdaptor.nickDuplicateCheck(nickname).getBody());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberName(Long memberNo,
                                 String name){
        memberAdaptor.requestMemberNameChange(memberNo, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberPhone(Long memberNo, String phone) {
        memberAdaptor.requestMemberPhoneChange(memberNo, phone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberPassword(Long memberNo, String password) {
        memberAdaptor.requestMemberPasswordChange(memberNo, password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberPasswordResponseDto getMemberPassword(Long memberNo) {
        return memberAdaptor.requestMemberPassword(memberNo);
    }
}
