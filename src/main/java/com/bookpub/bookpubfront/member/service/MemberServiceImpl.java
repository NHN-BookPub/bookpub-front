package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @Override
    public SignupMemberResponseDto signup(SignupMemberRequestDto signupMemberRequestDto) {
        String originPwd = signupMemberRequestDto.getPwd();
        String encodePwd = passwordEncoder.encode(originPwd);

        signupMemberRequestDto.setEncodePwd(encodePwd);

        ResponseEntity<SignupMemberResponseDto> exchange
                = memberAdaptor.signupRequest(signupMemberRequestDto);

        return exchange.getBody();
    }

    @Override
    public void modifyMemberNickName(Long memberNo, ModifyMemberNickNameRequestDto dto) {
        memberAdaptor.requestMemberNickNameChange(memberNo, dto);
    }

    @Override
    public void modifyMemberEmail(Long memberNo, ModifyMemberEmailRequestDto dto) {
        memberAdaptor.requestMemberEmailChange(memberNo, dto);
    }

    @Override
    public MemberDetailResponseDto getMember(Long memberNo){
        return memberAdaptor.requestMemberDetails(memberNo);
    }

    @Override
    public PageResponse<MemberResponseDto> getMembers(Pageable pageable){
        return memberAdaptor.requestMembers(pageable);
    }

    @Override
    public void memberBlock(Long memberNo){
        memberAdaptor.requestMemberBlock(memberNo);
    }
}
