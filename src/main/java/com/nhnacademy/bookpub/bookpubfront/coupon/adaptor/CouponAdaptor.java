package com.nhnacademy.bookpub.bookpubfront.coupon.adaptor;

import com.nhnacademy.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * api를 이용해 back 서버(shop)와 데이터를 주고받기 위해 만든 어댑터 인터페이스입니다.
 *
 * @author : 정유진, 김서현
 * @since : 1.0
 **/

public interface CouponAdaptor {

    /**
     * 쿠폰을 등록하기 위한 메서드입니다.
     *
     * @param createRequestDto 등록할 쿠폰 정보
     */
    void requestAddCoupon(CreateCouponRequestDto createRequestDto);

    /**
     * 쿠폰 페이지를 조회하기 위한 메소드입니다.
     *
     * @param pageable  페이지 정보
     * @param searchKey 검색 조건
     * @param search    검색어
     * @return 쿠폰 Dto 정보를 담은 페이지
     */
    PageResponse<GetCouponResponseDto> requestCoupons(Pageable pageable, String searchKey,
            String search);

    /**
     * 멤버의 사용가능한 쿠폰 페이지를 조회하기 위한 메소드입니다.
     *
     * @param pageable 페이지 정보
     * @param memberNo 멤버 번호
     * @return 멤버의 사용가능한 쿠폰 DTO 정보를 담은 페이지
     */
    PageResponse<GetCouponResponseDto> requestPositiveCoupons(Pageable pageable, Long memberNo);

    /**
     * 멤버의 사용 불가능한 쿠폰 페이지를 조회하기 위한 메소드입니다.
     *
     * @param pageable 페이지 정보
     * @param memberNo 멤버 번호
     * @return 멤버의 사용 불가능한 쿠폰 DTO 정보를 담은 페이지
     */
    PageResponse<GetCouponResponseDto> requestNegativeCoupons(Pageable pageable, Long memberNo);

    /**
     * 회원의 포인트 쿠폰 사용을 요청하는 메서드입니다.
     *
     * @param couponNo 쿠폰 번호
     */
    void requestModifyUsedPointCoupon(Long couponNo);

    /**
     * 멤버 번호로 등급 쿠폰 발급 유무를 확인하기 위한 메서드입니다.
     *
     * @param memberNo 멤버 번호
     * @return 등급 쿠폰 발급 유무
     */
    Boolean requestExistCouponsByMemberNo(Long memberNo, List<Long> tierCoupons);

    /**
     * 멤버에게 등급쿠폰을 발급하기 위한 메서드입니다.
     *
     * @param memberNo    멤버 번호
     * @param tierCoupons 등급 쿠폰 리스트
     */
    void requestIssueTierCoupons(Long memberNo, List<Long> tierCoupons);


    /**
     * 이달의 쿠폰 발급하기 위한 메서드입니다.
     *
     * @param memberNo   멤버 번호
     * @param templateNo 쿠폰 템플릿 번호
     */
    void requestIssueMonthCoupon(Long memberNo, Long templateNo);

    /**
     * 이달의 쿠폰 발급 여부 확인 메서드입니다.
     *
     * @param memberNo   멤버 번호
     * @param templateNo 쿠폰 템플릿 번호
     * @return 쿠폰 발급 여부
     */
    Boolean checkCouponMonthIssued(Long memberNo, Long templateNo);

    /**
     * 회원에 따른 이달의 쿠폰 중복 여부를 확인하기 위한 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @param templateList 이달의 쿠폰 쿠폰템플릿 번호 리스트
     * @return 중복 여부가 담긴 boolean 리스트
     */
    List<Boolean> requestCouponMonthDuplicate(Long memberNo, List<Long> templateList);
}
