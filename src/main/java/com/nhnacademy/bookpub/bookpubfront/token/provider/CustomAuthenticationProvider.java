package com.nhnacademy.bookpub.bookpubfront.token.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

/**
 * 커스텀하여 만든 유저 인증 provider.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Slf4j
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    /**
     * 아이디와 패스워드로 인증하는 메소드.
     *
     * @param authentication token의 상위 클래스
     * @return token(jwt가 아닌 security 인증토큰)을 반환.
     * @throws AuthenticationException 인증 스트림에 대한 인증이 실패한 경우 throw되는 예외.
     */
    @Override
    public Authentication authenticate(
            Authentication authentication) throws AuthenticationException {

        User userDetails
                = (User) this.getUserDetailsService()
                .loadUserByUsername((String) authentication.getPrincipal());

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }


}
