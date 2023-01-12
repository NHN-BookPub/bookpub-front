package com.bookpub.bookpubfront.member.adaptor;

import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * 멤버의 서버간 데이터 송신 및 수신을 담당하는 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface MemberAdaptor {

    ResponseEntity<SignupMemberResponseDto> signupRequest(SignupMemberRequestDto signupRequest);

    void requestMemberNickNameChange(Long memberNo, ModifyMemberNickNameRequestDto requestDto);

    void requestMemberEmailChange(Long memberNo, ModifyMemberEmailRequestDto requestDto);

    MemberDetailResponseDto requestMemberDetails(Long memberNo);

    PageResponse<MemberResponseDto> requestMembers(Pageable pageable);

    void requestMemberBlock(Long memberNo);
}
