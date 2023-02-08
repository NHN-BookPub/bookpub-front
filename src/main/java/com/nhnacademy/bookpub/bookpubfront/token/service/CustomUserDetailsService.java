package com.nhnacademy.bookpub.bookpubfront.token.service;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.AUTHENTICATION;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.SESSION_COOKIE;

import com.nhnacademy.bookpub.bookpubfront.dto.AuthDto;
import com.nhnacademy.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.exception.MemberNotFoundException;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * UserDetailService 클래스 상속받아 커스터마이징 클래스.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberAdaptor memberAdaptor;
    private final RedisTemplate<String, AuthDto> redisTemplate;


    /**
     * 사용자 정보를 담는 인터페이스인 UserDetail을 반환해주는 메소드.
     *
     * @param accessToken 인증받은 accessToken.
     * @return 사용자 정보가 담겨있는 UserDetail 클래스를 상속받은 Custom클래스.
     * @throws UsernameNotFoundException 가입하지 않은 계정으로 로그인 시도를 할 때 발생하는 exception.
     */
    @Override
    public UserDetails loadUserByUsername(String accessToken) throws UsernameNotFoundException {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        String sessionId = request.getSession().getId();
        CookieUtil.makeCookie(response, SESSION_COOKIE, sessionId);

        MemberDetailResponseDto member
                = memberAdaptor.requestAuthMemberInfo(accessToken);

        if (Objects.isNull(member)) {
            throw new MemberNotFoundException();
        }

        List<SimpleGrantedAuthority> authorities =
                Utils.makeAuthorities(member.getAuthorities());

        redisTemplate.opsForHash().put(AUTHENTICATION, sessionId, member);

        return new User(member.getMemberNo().toString(),
                "dummy",
                authorities);
    }
}
