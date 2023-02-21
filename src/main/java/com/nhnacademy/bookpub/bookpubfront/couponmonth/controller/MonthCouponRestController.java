package com.nhnacademy.bookpub.bookpubfront.couponmonth.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이달의 쿠폰 발급을 위한 RestController 입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class MonthCouponRestController {

    private final CouponService couponService;

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String COUPON = "COUPON";

    /**
     * 이달의 쿠폰 발급을 위한 api 입니다.
     *
     * @param memberNo   멤버 번호
     * @param templateNo 쿠폰 템플릿 번호
     * @return 1:중복 발급, 2: 수량 제한, 3: 발급 성공
     */
    @Auth
    @GetMapping("/coupon/month-coupon")
    public String issueMonthCoupon(@RequestParam Long memberNo, @RequestParam Long templateNo) {

        if (redisTemplate.opsForValue().get(COUPON + templateNo).equals(0)) {
            //수량이 없으면
            return "ZERO";
        } else {
            //수량이 존재하면 수량 감소 후 발급
            redisTemplate.opsForValue().decrement(COUPON + templateNo);
            couponService.issueMonthCoupon(memberNo, templateNo);
            return "OK";
        }

    }

    /**
     * 이달의 쿠폰 발급 확인 여부를 위한 api 입니다.
     *
     * @param memberNo   멤버 번호
     * @param templateNo 쿠폰 템플릿 번호
     * @return 발급 여부
     */
    @Auth
    @GetMapping("/coupon/month-coupon/check-issued")
    public boolean checkCouponMonthIssued(@RequestParam Long memberNo,
            @RequestParam Long templateNo) {
        return couponService.checkCouponMonthIssued(memberNo, templateNo);

    }

}
