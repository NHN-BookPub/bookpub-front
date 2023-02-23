package com.nhnacademy.bookpub.bookpubfront.member.service;

import com.nhnacademy.bookpub.bookpubfront.dto.AuthDto;
import com.nhnacademy.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.MemberAddressRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.OauthMemberCreateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberPasswordResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    private final RedisTemplate<String, AuthDto> redisTemplate;
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
    public SignupMemberResponseDto signup(OauthMemberCreateRequestDto signupMemberRequestDto) {
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
    public void logout(HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            Cookie jwtCookie = CookieUtil.findCookie(JwtUtil.JWT_COOKIE);
            if (Objects.isNull(jwtCookie)) {
                SecurityContextHolder.clearContext();
                return;
            }

            Cookie sessionCookie = CookieUtil.findCookie(Utils.SESSION_COOKIE);
            jwtCookie.setMaxAge(0);
            jwtCookie.setValue("");
            sessionCookie.setMaxAge(0);
            sessionCookie.setValue("");

            redisTemplate.opsForHash().delete(Utils.AUTHENTICATION, Utils.SESSION_COOKIE);

            response.addCookie(jwtCookie);
            response.addCookie(sessionCookie);
            SecurityContextHolder.clearContext();

            memberAdaptor.logout();
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
    public MemberDetailResponseDto getTokenMember(Long memberNo) {
        return memberAdaptor.requestTokenMemberDetails(memberNo);
    }

    @Override
    public MemberDetailResponseDto getApiMember(Long memberNo) {
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
    public void memberDelete(Long memberNo){
        memberAdaptor.requestMemberDelete(memberNo);
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
                                 String name) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyMemberAddress(Long memberNo, Long addressNo) {
        memberAdaptor.requestMemberBaseAddressChange(memberNo, addressNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMemberAddress(Long memberNo, MemberAddressRequestDto requestDto) {
        memberAdaptor.requestMemberAddressAdd(memberNo, requestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMemberAddress(Long memberNo, Long addressNo) {
        memberAdaptor.requestMemberAddressDelete(memberNo, addressNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTierByMemberNo(Long memberNo) {
        return memberAdaptor.requestMemberTier(memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<MemberResponseDto> getMembersById(Pageable pageable, String id) {
        return memberAdaptor.requestMembersById(pageable, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<MemberResponseDto> getMembersByNickName(Pageable pageable, String nickName) {
        return memberAdaptor.requestMembersByNickName(pageable, nickName);
    }
}
