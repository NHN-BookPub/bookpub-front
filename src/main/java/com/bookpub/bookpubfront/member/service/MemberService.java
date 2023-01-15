package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import javax.servlet.http.HttpSession;

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

    /** member 아이디, 패스워드를 통해 로그인 진행 메소드.
     *
     * @param loginMemberRequestDto 멤버의 아이디, 패스워드가 담겨있다.
     * @param session HTTPSession 객체.
     */
    void login(LoginMemberRequestDto loginMemberRequestDto, HttpSession session);
}
