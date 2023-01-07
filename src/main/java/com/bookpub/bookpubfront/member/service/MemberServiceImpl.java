package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.exception.SignUpFailedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 멤버 도메인의 서비스 메소드를 구현하는 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberAdaptor memberAdaptor;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SignupMemberRequestDto signup(SignupMemberResponseDto signupMemberResponseDto) {
        String originPwd = signupMemberResponseDto.getPwd();
        String encodePwd = passwordEncoder.encode(originPwd);

        signupMemberResponseDto.setEncodePwd(encodePwd);

        ResponseEntity<String> exchange
                = memberAdaptor.signupRequest(signupMemberResponseDto);

        SignupMemberRequestDto signUpMemberInfo = new SignupMemberRequestDto();

        try {
            signUpMemberInfo =
                    objectMapper.readValue(exchange.getBody(), SignupMemberRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new SignUpFailedException(signupMemberResponseDto.getMemberId());
        }

        return signUpMemberInfo;
    }
}
