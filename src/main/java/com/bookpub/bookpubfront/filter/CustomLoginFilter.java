package com.bookpub.bookpubfront.filter;

import static com.bookpub.bookpubfront.token.util.JwtUtil.makeJwtCookie;

import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 로그인 폼으로부터 id, pwd를 받아 auth서버에 보낸 후 jwt를 발급받는 필터.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Slf4j
@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final MemberAdaptor memberAdaptor;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String id = obtainUsername(request);
        String password = obtainPassword(request);

        LoginMemberRequestDto loginMemberRequestDto = new LoginMemberRequestDto(id, password);

        ResponseEntity<Void> jwtResponse
                = memberAdaptor.loginRequest(loginMemberRequestDto);

        Long expireTime = Long.parseLong(
                Objects.requireNonNull(
                        jwtResponse.getHeaders().get(JwtUtil.EXP_HEADER)).get(0)
        );

        String accessToken =
                Objects.requireNonNull(jwtResponse.getHeaders().get("Authorization"))
                        .get(0).substring(JwtUtil.TOKEN_TYPE.length());

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(accessToken, password);

        Cookie cookie =
                makeJwtCookie(accessToken, expireTime);

        response.addCookie(cookie);

        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException {
        SecurityContextHolder.clearContext();

        response.sendRedirect("/");
    }


}
