package com.nhnacademy.bookpub.bookpubfront.couponmonth.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.coupon.service.CouponService;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.service.CouponTemplateService;
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

    private final CouponTemplateService couponTemplateService;
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
    public Integer issueMonthCoupon(@RequestParam Long memberNo, @RequestParam Long templateNo) {
        // 수량 확인 - redis
        boolean issued = couponService.checkCouponIssuedByMemberNo(memberNo, templateNo);
        if (issued) {
            return 1;
        } else {
            if (redisTemplate.opsForValue().get(COUPON + templateNo).equals(0)) {
                return 2;
            } else {
                redisTemplate.opsForValue().decrement(COUPON + templateNo);
                couponService.issueMonthCoupon(memberNo, templateNo);

                // "redis 에서 수량 감소 시키고 insert 시키기" //db
                return 3;
            }
        }

    }

}
