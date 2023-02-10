package com.nhnacademy.bookpub.bookpubfront.filter;

import static com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil.makeJwtCookie;

import com.nhnacademy.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
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
    private static final String LOGIN_STATUS = "X-LOGIN";
    private String social;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String id = obtainUsername(request);
        String password = obtainPassword(request);
        social = obtainIsSocial(request);

        LoginMemberRequestDto loginMemberRequestDto = new LoginMemberRequestDto(id, password);

        ResponseEntity<Void> jwtResponse
                = memberAdaptor.loginRequest(loginMemberRequestDto);

        Long expireTime = getExpireTime(jwtResponse);
        String accessToken = getAccessToken(jwtResponse);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(accessToken, password);

        Cookie cookie = makeJwtCookie(accessToken, expireTime);

        response.addCookie(cookie);

        return getAuthenticationManager().authenticate(token);
    }

    /**
     * social 로그인인지 아닌지 확인하는 메소드.
     *
     * @param request request.
     * @return social 정보.
     */
    private static String obtainIsSocial(HttpServletRequest request) {
        return request.getParameter("social");
    }

    /**
     * response 헤더에서 accessToken 파싱하는 메소드.
     *
     * @param jwtResponse auth서버와의 통신 결과.
     * @return accessToken.
     */
    private static String getAccessToken(ResponseEntity<Void> jwtResponse) {
        return Objects.requireNonNull(jwtResponse.getHeaders()
                .get("Authorization")).get(0).substring(JwtUtil.TOKEN_TYPE.length());
    }

    /**
     * response 헤더에서 exp 파싱하는 메소드.
     *
     * @param jwtResponse auth와의 통신 결과.
     * @return exp time.
     */
    private static Long getExpireTime(ResponseEntity<Void> jwtResponse) {
        return Long.parseLong(
                Objects.requireNonNull(
                        jwtResponse.getHeaders().get(JwtUtil.EXP_HEADER)).get(0));
    }

    /**
     * 성공시 실행되는 메소드.
     *
     * @param request    request.
     * @param response   response.
     * @param chain      필터체인.
     * @param authResult 인증결과.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException {
        SecurityContextHolder.clearContext();
        if (isSocialLogin()) {
            response.addHeader(LOGIN_STATUS, "success");
        } else {
            response.sendRedirect("/");
        }
    }

    /**
     * social 계정으로 로그인 하는지.
     *
     * @return true, false.
     */
    private boolean isSocialLogin() {
        return Objects.nonNull(social);
    }
}
