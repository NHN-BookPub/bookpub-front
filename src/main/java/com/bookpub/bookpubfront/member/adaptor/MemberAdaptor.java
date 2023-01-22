package com.bookpub.bookpubfront.member.adaptor;

import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberLoginResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberPasswordResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * 멤버가 shop 이랑 http 통신을 하기위한 인터페이스 입니다.
 */
public interface MemberAdaptor {

    /**
     * 회원가입 여부의 값이 반환됩니다.
     *
     * @param signupRequest 회원가입 정보.
     * @return 성공 201 이 반환됩니다.
     */
    ResponseEntity<SignupMemberResponseDto> signupRequest(SignupMemberRequestDto signupRequest);

    /**
     * 멤버 닉네임을 변경하기위한 메서드입니다.
     *
     * @param memberNo  멤버 번호가 기입.
     * @param nickname 수정할 닉네임 기입.
     */
    void requestMemberNickNameChange(Long memberNo, String nickname);

    /**
     * 이메일을 변경할때 쓰이는 메서드입니다.
     *
     * @param memberNo   멤버 번호가 기입.
     * @param email 변경할 이메일 번호가 기입.
     */
    void requestMemberEmailChange(Long memberNo, String email);

    /**
     * 멤버의 상세정보를 받기위하여 쓰이는 메서드입니다.
     *
     * @param memberNo 멤버 번호가 기입
     * @return 멤버의 상세 정보가 반환됩니다.
     */
    MemberDetailResponseDto requestMemberDetails(Long memberNo);

    /**
     * 페이징 정보가 담긴 멤버들이 반환.
     *
     * @param pageable 페이징 정보 기입.
     * @return 페이징 객체에 담긴 멤버정보들을 반환.
     */
    PageResponse<MemberResponseDto> requestMembers(Pageable pageable);

    /**
     * 사용자의 차단을 위한 메서드입니다.
     *
     * @param memberNo 멤버 번호가기입.
     */
    void requestMemberBlock(Long memberNo);

    /**
     * 회원의 전체 통계가 나오는 메서드입니다.
     *
     * @return 회원의 통계가 반환.
     */
    MemberStatisticsResponseDto requestMemberStatics();

    /**
     * 회원의 등급별 추이가 반환됩니다.
     *
     * @return 회원의 등급별 추이가 반환.
     */
    List<MemberTierStatisticsResponseDto> requestMemberTierStatics();

    /** 로그인을 위해 auth 서버와 통신하는 메소드.
     *
     * @param loginRequest 로그인을 위한 멤버정보가 들어있는 요청 dto.
     * @return auth 서버에서 생성된 토큰을 헤더에 담아 응답해준다.
     */
    ResponseEntity<Void> loginRequest(LoginMemberRequestDto loginRequest);

    /**
     * 아이디 중복체크를 위해 통신 메소드.
     *
     * @param id 회원가입 아이디.
     * @return 중복여부 true, false
     */
    ResponseEntity<Boolean> idDuplicateCheck(String id);

    /**
     * 닉네임 중복체크를 위해 통신하는 메소드.
     *
     * @param nickname 회원가입 닉네임.
     * @return 중복여부 true, false
     */
    ResponseEntity<Boolean> nickDuplicateCheck(String nickname);

    /**
     * 회원의 이름을 수정하기위한 메서드입니다.
     *
     * @param memberNo 회원 번호 기입
     * @param name     회원 명
     */
    void requestMemberNameChange(Long memberNo, String name);

    /**
     * 회원의 휴대폰 번호를 수정하기위한 메서드입니다.
     *
     * @param memberNo 회원 번호 기입
     * @param phone    휴대전화 번호 기입
     */
    void requestMemberPhoneChange(Long memberNo, String phone);

    /**
     * 회원의 비밀 번호를 받기위해 사용되는 메서드 입니다.
     *
     * @param memberNo 회원의 번호가 기입.
     * @return 회원의 encoding 된 값이 반환됩니다.
     */
    MemberPasswordResponseDto requestMemberPassword(Long memberNo);

    void requestMemberPasswordChange(Long memberNo, String password);

    MemberLoginResponseDto requestAuthMemberInfo(HttpServletRequest request);
}
