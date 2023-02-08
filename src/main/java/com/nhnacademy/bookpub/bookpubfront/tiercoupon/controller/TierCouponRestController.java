package com.nhnacademy.bookpub.bookpubfront.tiercoupon.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.coupon.service.CouponService;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.service.TierCouponService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 등급 쿠폰을 발급하기 위한 Controller 입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class TierCouponRestController {

    private final TierCouponService tierCouponService;
    private final CouponService couponService;

    /**
     * 등급 번호로 해당 쿠폰 템플릿 번호 리스트를 조회합니다. 멤버 해당 등급쿠폰을 발급 받았는지 확인합니다. 발급 받지 않은 경우만 발급하는 메소드입니다.
     *
     * @param memberNo 멤버 번호
     * @param tierNo   등급 번호
     * @return 발급 성공 여부
     */
    @Auth
    @GetMapping("/coupon/tier-coupon")
    public boolean issueTierCoupon(@RequestParam Long memberNo, @RequestParam Integer tierNo) {
        // 등급 쿠폰 조회 // get
        List<Long> tierCoupons = tierCouponService.getTierCouponsByTierNo(tierNo);

        //쿠폰 함에서 회원 번호를 가지고 발급 받았는지 조회 => boolean //get
        boolean existTierCoupons = couponService.existCouponsByMemberNo(memberNo, tierCoupons);

        if (existTierCoupons) {
            return false;
        } else {
            couponService.issueTierCoupons(memberNo, tierCoupons);
            return true;
        }

    }

}
