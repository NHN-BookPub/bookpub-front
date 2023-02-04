package com.nhnacademy.bookpub.bookpubfront.tiercoupon.service;

import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.request.CreateTierCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.response.GetTierCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * 등급 쿠폰을 다루기 위한 서비스 인터페이스입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
public interface TierCouponService {

    /**
     * 등급 쿠폰 페이지를 조회하기 위한 메서드 입니다.
     *
     * @param pageable 페이지.
     * @return 등급 쿠폰 정보 DTO를 담은 페이지.
     */
    PageResponse<GetTierCouponResponseDto> getTierCoupons(Pageable pageable);

    /**
     * 등급 쿠폰 생성하기 위한 메서드입니다.
     *
     * @param createRequestDto 쿠폰 생성에 필요한 정보를 담은 DTO.
     */
    void createTierCoupon(CreateTierCouponRequestDto createRequestDto);

    /**
     * 해당 등급 쿠폰을 삭제하기 위한 메서드입니다.
     *
     * @param tierNo     등급 번호.
     * @param templateNo 쿠폰 템플릿 번호.
     */
    void deleteTierCoupon(Integer tierNo, Long templateNo);

    /**
     * 등급 번호로 쿠폰 템플릿 번호 리스트를 조회하기 위한 메서드입니다.
     *
     * @param tierNo 등급 번호
     * @return 쿠폰 템플릿 리스트
     */
    List<Long> getTierCouponsByTierNo(Integer tierNo);
}
