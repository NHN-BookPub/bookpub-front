package com.bookpub.bookpubfront.filter;

import static com.bookpub.bookpubfront.utils.Utils.AUTHENTICATION;
import static com.bookpub.bookpubfront.utils.Utils.SESSION_COOKIE;

import com.bookpub.bookpubfront.dto.AuthDto;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import com.bookpub.bookpubfront.utils.Utils;
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
            log.warn("error");

            if (request.getRequestURI().contains(".js")
                    || request.getRequestURI().contains(".css")
                    || request.getRequestURI().contains(".png")) {
                filterChain.doFilter(request, response);
                return;
            }
            log.warn("error2");

            Cookie sessionCookie = Utils.findCookie(SESSION_COOKIE);
            if (Objects.isNull(sessionCookie)) {
                log.warn("session Cookie null");
                filterChain.doFilter(request, response);
                log.warn("error 1223");
                return;
            }
            String sessionId = Objects.requireNonNull(sessionCookie).getValue();
            log.warn(sessionId);
            AuthDto auth =
                    (AuthDto) redisTemplate.opsForHash().get(AUTHENTICATION, sessionId);

            if (Objects.isNull(auth)) {
                filterChain.doFilter(request, response);
                return;
            }
            log.warn(auth.toString());

            if (Objects.isNull(Utils.findCookie(JwtUtil.JWT_COOKIE))) {
                filterChain.doFilter(request, response);
                return;
            }

            List<SimpleGrantedAuthority> authorities = Utils.makeAuthorities(auth.getAuthorities());
            log.warn(authorities.toString());
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(
                    auth.getMemberNo(),
                    auth.getMemberPwd(),
                    authorities)
            );

            log.warn(context.toString());


            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
