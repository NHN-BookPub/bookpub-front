package com.bookpub.bookpubfront.config;

import com.bookpub.bookpubfront.filter.CustomAuthenticationFilter;
import com.bookpub.bookpubfront.filter.CustomLoginFilter;
import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.token.provider.CustomAuthenticationProvider;
import com.bookpub.bookpubfront.token.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final MemberAdaptor memberAdaptor;
    private final CustomUserDetailsService userDetailsService;

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
//                .antMatchers("/", "/login", "/signup").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
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
        return new CustomAuthenticationFilter();
    }

//    @Bean
//    public CustomExpConfirmFilter customExpConfirmFilter() {
//        return new CustomExpConfirmFilter(memberAdaptor, objectMapper);
//    }

    @Bean
    public CustomLoginFilter customLoginFilter(AuthenticationManager authenticationManager) {
        CustomLoginFilter loginFilter
                = new CustomLoginFilter(memberAdaptor, authenticationProvider());
        loginFilter.setFilterProcessesUrl("/auth");
        loginFilter.setAuthenticationManager(authenticationManager);
        loginFilter.setUsernameParameter("id");
        loginFilter.setPasswordParameter("pwd");

        return loginFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

}
