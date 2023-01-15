package com.bookpub.bookpubfront.member.service;

import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
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
     * member 아이디, 패스워드를 통해 로그인 진행 메소드.
     *
     * @param loginMemberRequestDto 멤버의 아이디, 패스워드가 담겨있다.
     * @param session               HTTPSession 객체.
     */
    void login(LoginMemberRequestDto loginMemberRequestDto, HttpSession session);

    /**
     * 회원 로그아웃 메소드.
     *
     * @param session HttpSession 객체.
     */
    void logout(HttpSession session);


    /**
     * 멤버의 닉네임을 적어야하는 메서드입니다.
     *
     * @param memberNo 멤버 번호.
     * @param dto      수정할 닉네임 기입.
     */
    void modifyMemberNickName(Long memberNo, ModifyMemberNickNameRequestDto dto);

    /**
     * 멤버의 이메일 정보를 수정해야하는 메서드입니다.
     *
     * @param memberNo 멤버 번호가 기입.
     * @param dto      멤버가 수정할 이메일 정보.
     */
    void modifyMemberEmail(Long memberNo, ModifyMemberEmailRequestDto dto);

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
     * 회원의 등급별 통계를 반환
     *
     * @return the list
     */
    List<MemberTierStatisticsResponseDto> memberTierStatistics();
}
