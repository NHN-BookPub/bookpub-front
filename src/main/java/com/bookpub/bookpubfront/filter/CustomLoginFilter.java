package com.bookpub.bookpubfront.filter;

import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.token.exception.TokenNotIssuedException;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
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
    private final AuthenticationProvider provider;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String id = obtainUsername(request);
        String password = obtainPassword(request);

        LoginMemberRequestDto loginMemberRequestDto = new LoginMemberRequestDto(id, password);

        ResponseEntity<Void> jwtResponse = memberAdaptor.loginRequest(loginMemberRequestDto);
        List<String> authorization = jwtResponse.getHeaders().get("Authorization");
        Optional<String> accessToken = Optional.of(Objects.requireNonNull(authorization).get(0));

        HttpSession session = request.getSession();
        session.setAttribute(JwtUtil.JWT_SESSION, accessToken.orElseThrow(TokenNotIssuedException::new));

        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(id, password);

        return provider.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        SecurityContextHolder.clearContext();
        response.sendRedirect("/");
    }
}
