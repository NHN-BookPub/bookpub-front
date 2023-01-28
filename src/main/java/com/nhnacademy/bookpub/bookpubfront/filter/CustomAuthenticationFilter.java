package com.nhnacademy.bookpub.bookpubfront.filter;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.AUTHENTICATION;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.SESSION_COOKIE;

import com.nhnacademy.bookpub.bookpubfront.dto.AuthDto;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import java.util.Objects;
import javax.servlet.FilterChain;
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
            if (request.getRequestURI().contains(".js")
                    || request.getRequestURI().contains(".css")
                    || request.getRequestURI().contains(".png")) {
                filterChain.doFilter(request, response);
                return;
            }

            Cookie sessionCookie = Utils.findCookie(SESSION_COOKIE);
            if (Objects.isNull(sessionCookie)) {
                filterChain.doFilter(request, response);
                return;
            }
            String sessionId = Objects.requireNonNull(sessionCookie).getValue();

            AuthDto auth =
                    (AuthDto) redisTemplate.opsForHash().get(AUTHENTICATION, sessionId);

            if (Objects.isNull(auth)) {
                filterChain.doFilter(request, response);
                return;
            }

            if (Objects.isNull(Utils.findCookie(JwtUtil.JWT_COOKIE))) {
                filterChain.doFilter(request, response);
                return;
            }

            List<SimpleGrantedAuthority> authorities = Utils.makeAuthorities(auth.getAuthorities());

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(
                    auth.getMemberNo(),
                    auth.getMemberPwd(),
                    authorities)
            );


            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
