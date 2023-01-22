package com.bookpub.bookpubfront.token.service;


import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.response.MemberLoginResponseDto;
import com.bookpub.bookpubfront.member.exception.MemberNotFoundException;
import com.bookpub.bookpubfront.token.CustomUserDetails;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public static final String PRINCIPAL = "principal";
    public static final String AUTHORITIES = "principal";


    /**
     * 사용자 정보를 담는 인터페이스인 UserDetail을 반환해주는 메소드.
     *
     * @param userId 로그인 한 user의 아이디.
     * @return 사용자 정보가 담겨있는 UserDetail 클래스를 상속받은 Custom클래스.
     * @throws UsernameNotFoundException 가입하지 않은 계정으로 로그인 시도를 할 때 발생하는 exception.
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        MemberLoginResponseDto memberLoginResponseDto
                = memberAdaptor.requestAuthMemberInfo(request);

        if (Objects.isNull(memberLoginResponseDto)) {
            throw new MemberNotFoundException();
        }

        HttpSession session = request.getSession();
        session.setAttribute(PRINCIPAL, String.valueOf(memberLoginResponseDto.getMemberNo()));
        session.setAttribute(AUTHORITIES, memberLoginResponseDto.getAuthorities().toString());

        return new CustomUserDetails(memberLoginResponseDto);
    }
}
