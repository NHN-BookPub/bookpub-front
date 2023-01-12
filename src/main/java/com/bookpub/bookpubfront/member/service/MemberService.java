package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 멤버 도메인의 서비스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface MemberService {

    /**
     * member 패스워드 암호화 후 shop 서버에 저장 요청하는 메소드.
     *
     * @param signupMemberRequestDto 멤버 정보를 담아 요청보내는 DTO
     * @return 저장된 멤버 정보의 일부를 반환받는 DTO
     */
    SignupMemberResponseDto signup(SignupMemberRequestDto signupMemberRequestDto);


    void modifyMemberNickName(Long memberNo, ModifyMemberNickNameRequestDto dto);

    void modifyMemberEmail(Long memberNo, ModifyMemberEmailRequestDto dto);

    MemberDetailResponseDto getMember(Long memberNo);

    PageResponse<MemberResponseDto> getMembers(Pageable pageable);

    void memberBlock(Long memberNo);
}
