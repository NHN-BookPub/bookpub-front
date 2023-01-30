package com.nhnacademy.bookpub.bookpubfront.member.service;

import com.nhnacademy.bookpub.bookpubfront.member.dto.request.MemberAddressRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.OauthMemberCreateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberPasswordResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.oauth.dto.request.OauthMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;

/**
 * 멤버 도메인의 서비스.
 *
 * @author : 임태원
 * @since : 1.0
 */
public interface MemberService {

    /**
     * member 패스워드 암호화 후 shop 서버에 저장 요청하는 메소드.
     *
     * @param signupMemberRequestDto 멤버 정보를 담아 요청보내는 DTO
     * @return 저장된 멤버 정보의 일부를 반환받는 DTO
     */
    SignupMemberResponseDto signup(SignupMemberRequestDto signupMemberRequestDto);

    /**
     * oauth 회원정보를 저장 요청하는 메소드.
     *
     * @param signupMemberRequestDto 멤버 정보를 담아 요청보내는 DTO
     * @return 저장된 멤버 정보의 일부를 반환받는 DTO
     */
    SignupMemberResponseDto signup(OauthMemberCreateRequestDto signupMemberRequestDto);

    /**
     * 회원 로그아웃 메소드.
     *
     * @param session HttpSession 객체.
     */
    void logout(HttpServletResponse response, HttpSession session);


    /**
     * 멤버의 닉네임을 적어야하는 메서드입니다.
     *
     * @param memberNo 멤버 번호.
     * @param nickname 수정할 닉네임 기입.
     */
    void modifyMemberNickName(Long memberNo, String nickname);

    /**
     * 멤버의 이메일 정보를 수정해야하는 메서드입니다.
     *
     * @param memberNo 멤버 번호가 기입.
     * @param email    멤버가 수정할 이메일 정보.
     */
    void modifyMemberEmail(Long memberNo, String email);

    /**
     * 멤버의 단일값 반환.
     *
     * @param memberNo 멤버 번호가 기입.
     * @return 멤버의 상세정보가 반환.
     */
    MemberDetailResponseDto getMember(Long memberNo);

    /**
     * 멤버들의 정보를 list 로 받는 메서드입니다.
     *
     * @param pageable 페이징 정보가 기입.
     * @return 페이징 정보가 기입된 멤버정보가 반환.
     */
    PageResponse<MemberResponseDto> getMembers(Pageable pageable);

    /**
     * 멤버의 차단을 위한 메서드입니다.
     *
     * @param memberNo 멤버 번호가 기입.
     */
    void memberBlock(Long memberNo);

    /**
     * 회원들의 통계를 반환합니다.
     *
     * @return 회원의 통계를 반환.
     */
    MemberStatisticsResponseDto memberStatistics();

    /**
     * 회원의 등급별 통계를 반환.
     *
     * @return the list
     */
    List<MemberTierStatisticsResponseDto> memberTierStatistics();

    boolean idDuplicateCheck(String id);

    boolean nickDuplicateCheck(String id);

    /**
     * 멤버에 이름을 수정할때 쓰이는 메서드입니다.
     *
     * @param memberNo 회원번호
     * @param name     이름
     */
    void modifyMemberName(Long memberNo, String name);

    /**
     * 멤버의 휴대전화번호를 수정할때 쓰이는 메서드입니다.
     *
     * @param memberNo 회원번호
     * @param phone    변경할 전화번호
     */
    void modifyMemberPhone(Long memberNo, String phone);

    /**
     * 회원의 비밀번호가 수정될때 쓰이는 메서드입니다.
     *
     * @param memberNo 회원번호
     * @param password raw 한 비밀번호
     */
    void modifyMemberPassword(Long memberNo, String password);

    /**
     * 멤버의 패스워드를 받아 처리하는 메서드입니다.
     *
     * @param memberNo 회원번호
     * @return 회원의 encoding 된 비밀번호 반환.
     */
    MemberPasswordResponseDto getMemberPassword(Long memberNo);

    /**
     * 회원의 베이스 주소지를 변경하기위한 메서드입니다.
     *
     * @param memberNo  회원번호
     * @param addressNo 기준 주소지가 될 주소번호
     */
    void modifyMemberAddress(Long memberNo, Long addressNo);

    /**
     * 회원의 주소를 추가하기위한 메서드입니다.
     *
     * @param memberNo   the member no
     * @param requestDto the request dto
     */
    void addMemberAddress(Long memberNo, MemberAddressRequestDto requestDto);

    /**
     * 회원의 주소를 삭제하기위한 메서드입니다.
     *
     * @param memberNo  회원번호
     * @param addressNo 주소번호
     */
    void deleteMemberAddress(Long memberNo, Long addressNo);

    /**
     * string 타입으로 들어온 유저정보를 dto로 파싱합니다.
     *
     * @param oauthMember 로그인하려는 유저 정보.
     * @return dto.
     */
    OauthMemberRequestDto oauthMemberParsing(String oauthMember);
}
