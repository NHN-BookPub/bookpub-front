package com.nhnacademy.bookpub.bookpubfront.member.adaptor;

import com.nhnacademy.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.MemberAddressRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.OauthMemberCreateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberPasswordResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
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
     * oauth 회원가입 여부의 값이 반환됩니다.
     *
     * @param signupRequest 회원가입 정보.
     * @return 성공 201 이 반환됩니다.
     */
    ResponseEntity<SignupMemberResponseDto> signupRequest(
            OauthMemberCreateRequestDto signupRequest);

    /**
     * 멤버 닉네임을 변경하기위한 메서드입니다.
     *
     * @param memberNo 멤버 번호가 기입.
     * @param nickname 수정할 닉네임 기입.
     */
    void requestMemberNickNameChange(Long memberNo, String nickname);

    /**
     * 이메일을 변경할때 쓰이는 메서드입니다.
     *
     * @param memberNo 멤버 번호가 기입.
     * @param email    변경할 이메일 번호가 기입.
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
     * 인가된 멤버의 상세정보를 받기위하여 쓰이는 메서드입니다.
     *
     * @param memberNo 멤버 번호가 기입
     * @return 멤버의 상세 정보가 반환됩니다.
     */
    MemberDetailResponseDto requestTokenMemberDetails(Long memberNo);

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
     * 멤버의 삭제를 위한 메서드입니다.
     *
     * @param memberNo the member no
     */
    void requestMemberDelete(Long memberNo);

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

    /**
     * 로그인을 위해 shop 서버와 통신하는 메소드.
     *
     * @param loginRequest 로그인을 위한 멤버정보가 들어있는 요청 dto.
     * @return auth 서버에서 생성된 토큰을 헤더에 담아 응답해준다.
     */
    ResponseEntity<Void> loginRequest(LoginMemberRequestDto loginRequest);

    /**
     * 유저가 활동중일 때 토큰의 만료기간이 가까워지면 재발급 요청하는 메소드.
     *
     * @param accessToken 유저의 인증 토큰.
     * @return auth 서버에서 재발급된 토큰을 헤더에 담아 응답해 준다.
     */
    ResponseEntity<Void> tokenReIssueRequest(String accessToken);

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

    /**
     * 회원의 비밀번호를 변경할때 사용되는 메서드입니다.
     *
     * @param memberNo 회원의 번호
     * @param password 변경할 raw 패스워드
     */
    void requestMemberPasswordChange(Long memberNo, String password);

    /**
     * 회원의 베이스 주소를 변경할때 사용되는 메서드입니다.
     *
     * @param memberNo  회원 번호
     * @param addressNo 변경할 raw 패스워드
     */
    void requestMemberBaseAddressChange(Long memberNo, Long addressNo);


    /**
     * 회원의 주소를 추가하기위한 메서드입니다.
     *
     * @param memberNo   회원번호가 기입됩니다.
     * @param requestDto 새로 생성될 주소에대한 정보가 들어있습니다.
     */
    void requestMemberAddressAdd(Long memberNo, MemberAddressRequestDto requestDto);

    /**
     * 회원의 주소정보를 삭제하기위한 메서드입니다.
     *
     * @param memberNo  회원번호
     * @param addressNo 주소번호
     */
    void requestMemberAddressDelete(Long memberNo, Long addressNo);

    /**
     * shop서버에 accessToken을 보내 멤버의 정보를 얻어오는 메소드.
     *
     * @param accessToken accessToken -> 추후 없어질 예정.
     * @return 인증받은 유저의 정보.
     */
    MemberDetailResponseDto requestAuthMemberInfo(String accessToken);

    /**
     * 회원의 멤버 등급을 조회하기 위한 메서드입니다.
     *
     * @param memberNo 멤버 번호
     * @return 등급 번호
     */
    Integer requestMemberTier(Long memberNo);

    /**
     * 로그아웃.
     */
    void logout();

    /**
     * 아이디로 멤버를 검색합니다.
     *
     * @param pageable 페이징
     * @param id 검색할 문자
     * @return 멤버리스트
     */
    PageResponse<MemberResponseDto> requestMembersById(Pageable pageable, String id);

    /**
     * 닉네임으로 멤버를 검색합니다.
     *
     * @param pageable 페이징
     * @param nickName 검색할 문자
     * @return 멤버리스트
     */
    PageResponse<MemberResponseDto> requestMembersByNickName(Pageable pageable, String nickName);
}
