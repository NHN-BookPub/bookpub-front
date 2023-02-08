package com.nhnacademy.bookpub.bookpubfront.filter;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.AUTHENTICATION;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.SESSION_COOKIE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.dto.AuthDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * front 서버의 로그인을 위한 커스텀 필터.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final RedisTemplate<String, AuthDto> redisTemplate;
    private final ObjectMapper objectMapper;

    /**
     * 인증된 사용자면 로그인 상태 유지.
     *
     * @param request     요청 정보 객체.
     * @param response    응답 정보 객체.
     * @param filterChain security의 필터체인 객체.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            if (notControllerUri(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            Cookie sessionCookie = CookieUtil.findCookie(SESSION_COOKIE);
            if (notExistCookie(request, response, filterChain, sessionCookie)) {
                return;
            }

            Cookie jwtCookie = CookieUtil.findCookie(JwtUtil.JWT_COOKIE);
            if (notExistCookie(request, response, filterChain, jwtCookie)) {
                return;
            }

            String sessionId = Objects.requireNonNull(sessionCookie).getValue();
            MemberDetailResponseDto member = (MemberDetailResponseDto)
                    redisTemplate.opsForHash().get(AUTHENTICATION, sessionId);
            if (notExistLoginData(request, response, filterChain, member)) {
                return;
            }

            List<SimpleGrantedAuthority> authorities =
                    Utils.makeAuthorities(Objects.requireNonNull(member).getAuthorities());

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(
                    member.getMemberNo().toString(),
                    objectMapper.writeValueAsString(member),
                    authorities)
            );

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    /**
     * 세션에 로그인 한 정보가 있는지 없는지 확인하는 메소드.
     *
     * @param request     request.
     * @param response    response.
     * @param filterChain filterchain.
     * @param member      로그인 한 유저의 정보.
     * @return 정보가 있는지 없는지.
     * @throws IOException      doFilter 발생가능 에러.
     * @throws ServletException doFilter 발생가능 에러.
     */
    private static boolean notExistLoginData(HttpServletRequest request,
                                             HttpServletResponse response,
                                             FilterChain filterChain,
                                             MemberDetailResponseDto member)
            throws IOException, ServletException {

        if (Objects.isNull(member)) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    /**
     * 브라우저에 쿠키가 있는지 없는지 확인하는 메소드.
     *
     * @param request     request.
     * @param response    response.
     * @param filterChain filterchain.
     * @param cookie      필요한 cookie.
     * @return 쿠키가 있는지 없는지.
     * @throws IOException      doFilter 발생가능 에러.
     * @throws ServletException doFilter 발생가능 에러.
     */
    private static boolean notExistCookie(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain filterChain,
                                          Cookie cookie) throws IOException, ServletException {
        if (Objects.isNull(cookie)) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    /**
     * filter에 들어오면 안되는 요청들.
     *
     * @param request request.
     * @return 필요한 요청인지 아닌지.
     */
    private static boolean notControllerUri(HttpServletRequest request) {
        return request.getRequestURI().contains("/static")
                || request.getRequestURI().equals("/error");
    }
}
