package com.bookpub.bookpubfront.filter;

import com.bookpub.bookpubfront.token.util.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private static final String CREDENTIAL = "dummy";
    /**
     * 특정 조건에서 필터가 작동하여 로그인 진행.
     *
     * @param request     요청 정보 객체.
     * @param response    응답 정보 객체.
     * @param filterChain security의 필터체인 객체.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            HttpSession session = request.getSession(false);
            if (Objects.isNull(session)) {
                filterChain.doFilter(request, response);
                return;
            }

            if (Objects.isNull(session.getAttribute(JwtUtil.JWT_SESSION))) {
                filterChain.doFilter(request,response);
                return;
            }

            String principal = (String) session.getAttribute("principal");
            String authorities = (String) session.getAttribute("authorities");

            if (Objects.isNull(principal) || Objects.isNull(authorities)) {
                filterChain.doFilter(request, response);
                return;
            }

            uploadSecurityContext(principal, authorities);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private static void uploadSecurityContext(String principal, String authorities) {
        String concatRoles = authorities.substring(1, authorities.length() - 1);
        String[] roles = concatRoles.split(",");

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (String role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.trim()));
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                principal,
                CREDENTIAL,
                grantedAuthorities
        );

        SecurityContextHolder.getContext().setAuthentication(token);
    }


}
