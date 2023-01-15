package com.bookpub.bookpubfront.config;

import com.bookpub.bookpubfront.token.filter.CustomAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Security 설정 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final ObjectMapper objectMapper;

    /**
     * security filterChain 설정
     *
     * @param http 간단하게 시큐리티 설정을 할 수있도록 제공해주는 파라미터.
     * @return 필터의 설정을 마친 후 필터체인을 리턴.
     * @throws Exception 필터가 작동되는 과정에서 발생되는 에러
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/", "/login", "/signup").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();
        http.csrf().disable();
        http.cors().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAfter(customAuthenticationFilter(), FilterSecurityInterceptor.class);

        http.formLogin()
                .disable();
        http.logout().disable();

        return http.build();
    }

    /**
     * 유저의 비밀번호를 암호화, 검증 해주는 메소드 빈.
     *
     * @return BCryptEncoder를 반환.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 토큰을 securityContextHolder에 넣어줄 필터.
     *
     * @return 커스텀한 인증필터를 반환한다.
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {
        return new CustomAuthenticationFilter(objectMapper);
    }
}
