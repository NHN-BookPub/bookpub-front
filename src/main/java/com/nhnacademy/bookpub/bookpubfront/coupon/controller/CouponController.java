package com.nhnacademy.bookpub.bookpubfront.coupon.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 마이페이지의 쿠폰을 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    /**
     * 마이페이지에서 포인트 쿠폰 사용을 위한 메서드입니다.
     *
     * @param couponNo the coupon no
     * @return the string
     */
    @Auth
    @PostMapping("/members/coupons/{couponNo}/point")
    public String usedPointCoupon(@PathVariable("couponNo") Long couponNo) {
        couponService.modifyUsedPointCoupon(couponNo);

        return "redirect:/members/coupon/positive";
    }
}
