package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.token.exception.TokenNotIssuedException;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Override
    public SignupMemberResponseDto signup(SignupMemberRequestDto signupMemberRequestDto) {
        String originPwd = signupMemberRequestDto.getPwd();
        String encodePwd = passwordEncoder.encode(originPwd);

        signupMemberRequestDto.setEncodePwd(encodePwd);

        ResponseEntity<SignupMemberResponseDto> exchange
                = memberAdaptor.signupRequest(signupMemberRequestDto);

        return exchange.getBody();
    }

    @Override
    public void login(LoginMemberRequestDto loginMemberRequestDto, HttpSession session) {
        ResponseEntity<Void> jwtResponse = memberAdaptor.loginRequest(loginMemberRequestDto);

        String accessToken = Objects.requireNonNull(jwtResponse.getHeaders().get("Authorization")).get(0);

        if(Objects.isNull(accessToken)){
            throw new TokenNotIssuedException();
        }

        session.setAttribute(JwtUtil.JWT_SESSION, accessToken);
    }
}
