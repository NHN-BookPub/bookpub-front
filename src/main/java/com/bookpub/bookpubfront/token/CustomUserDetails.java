package com.bookpub.bookpubfront.token;

import com.bookpub.bookpubfront.member.dto.response.MemberLoginResponseDto;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 유저정보를 담고있는 userDetail interface 의 구현체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final MemberLoginResponseDto member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getAuthorities().stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return member.getMemberPwd();
    }

    @Override
    public String getUsername() {
        return member.getMemberNo().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
