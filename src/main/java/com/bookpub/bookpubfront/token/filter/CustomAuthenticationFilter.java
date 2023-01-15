package com.bookpub.bookpubfront.token.filter;

import com.bookpub.bookpubfront.token.dto.TokenInfoDto;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Some description here
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        try {
            HttpSession session = request.getSession(false);

            if (Objects.isNull(session)) {
                filterChain.doFilter(request, response);
                return;
            }

            String accessToken = (String) session.getAttribute(JwtUtil.JWT_SESSION);

            if (Objects.isNull(accessToken)) {
                filterChain.doFilter(request, response);
                return;
            }

            String payload = JwtUtil.decodeJwt(accessToken);
            TokenInfoDto tokenInfo = mapper.readValue(payload, TokenInfoDto.class);

            String concatRoles = tokenInfo.getRoles().substring(1, tokenInfo.getRoles().length() - 1);
            String[] roles = concatRoles.split(",");

            List<GrantedAuthority> authorities = new ArrayList<>();

            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(tokenInfo.getMemberUUID(), session.getId(), authorities);

            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
