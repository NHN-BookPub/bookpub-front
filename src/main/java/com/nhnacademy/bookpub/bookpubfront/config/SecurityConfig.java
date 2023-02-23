package com.nhnacademy.bookpub.bookpubfront.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.dto.AuthDto;
import com.nhnacademy.bookpub.bookpubfront.filter.CustomAuthenticationFilter;
import com.nhnacademy.bookpub.bookpubfront.filter.CustomLoginFilter;
import com.nhnacademy.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.nhnacademy.bookpub.bookpubfront.token.provider.CustomAuthenticationProvider;
import com.nhnacademy.bookpub.bookpubfront.token.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

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
    private final MemberAdaptor memberAdaptor;
    private final CustomUserDetailsService userDetailsService;
    private final RedisTemplate<String, AuthDto> redisTemplate;
    private final ObjectMapper objectMapper;

    /**
     * security filterChain 설정.
     *
     * @param http 간단하게 시큐리티 설정을 할 수있도록 제공해주는 파라미터.
     * @return 필터의 설정을 마친 후 필터체인을 리턴.
     * @throws Exception 필터가 작동되는 과정에서 발생되는 에러
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager
                = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

        http.authorizeRequests()
                .antMatchers("/", "/login", "/signup").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                .csrf()
                .disable().cors().disable()
                .formLogin()
                .disable()
                .logout()
                .disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAt(customLoginFilter(authenticationManager),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(customAuthenticationFilter(), SecurityContextPersistenceFilter.class);

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

    /**
     * 토큰을 securityContextHolder에 넣어줄 필터.
     *
     * @return 커스텀한 인증필터를 반환한다.
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {
        return new CustomAuthenticationFilter(redisTemplate, objectMapper);
    }

    /**
     * UsernamePasswordAuthenticationFilter를 상속받는 필터
     * 로그인 폼에서 id,pwd를 request로 auth서버와 통신하여 jwt를 발급받는 필터.
     *
     * @param authenticationManager token발행을 위한 로직이 실행되게 도와주는 클래스.
     * @return 커스텀한 로그인 필터.
     */
    @Bean
    public CustomLoginFilter customLoginFilter(AuthenticationManager authenticationManager) {
        CustomLoginFilter loginFilter
                = new CustomLoginFilter(memberAdaptor);
        loginFilter.setFilterProcessesUrl("/auth");
        loginFilter.setAuthenticationManager(authenticationManager);
        loginFilter.setUsernameParameter("id");
        loginFilter.setPasswordParameter("pwd");

        return loginFilter;
    }

    /**
     * login필터에 들어갈 authenticationManager의 빈 등록 메소드.
     *
     * @param authenticationConfiguration authenticationManger를 관리하는 config 클래스.
     * @return authentication manager.
     * @throws Exception authentication manager를 가져오는 과정에서 발생되는 에러.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * authentication manager가 실행시킬 authenticate의 구현을 담당한 provider.
     * manager는 등록된 provider를 순차탐색하여 적절한 provider를 선택하여 실행시킨다.
     *
     * @return 커스텀 한 authenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

}
