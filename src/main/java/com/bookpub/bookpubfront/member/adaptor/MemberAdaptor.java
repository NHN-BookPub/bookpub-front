package com.bookpub.bookpubfront.member.adaptor;

import com.bookpub.bookpubfront.member.dto.request.ModifyMemberEmailRequestDto;
import com.bookpub.bookpubfront.member.dto.request.ModifyMemberNickNameRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.utils.PageResponse;
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
     * 멤버 닉네임을 변경하기위한 메서드입니다.
     *
     * @param memberNo  멤버 번호가 기입.
     * @param requestDto 수정할 닉네임 기입.
     */
    void requestMemberNickNameChange(Long memberNo, ModifyMemberNickNameRequestDto requestDto);

    /**
     * 이메일을 변경할때 쓰이는 메서드입니다.
     *
     * @param memberNo   멤버 번호가 기입.
     * @param requestDto 변경할 이메일 번호가 기입.
     */
    void requestMemberEmailChange(Long memberNo, ModifyMemberEmailRequestDto requestDto);

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
}
