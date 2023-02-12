package com.nhnacademy.bookpub.bookpubfront.couponmonth.controller;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.response.GetCouponMonthResponseDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.service.CouponMonthService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import java.util.List;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 이달의 쿠폰 이벤트 페이지를 위한 컨트롤러.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class MonthCouponController {

    private final CouponMonthService couponMonthService;

    private final CartUtils cartUtils;

    private final CategoryUtils categoryUtils;

    private static final String CART = "CART";
    private static final String COUPON = "COUPON";

    private final RedisTemplate<String, Object> redisTemplate;

    private final MemberUtils memberUtils;

    /**
     * 이달의 쿠폰 발급을 위한 페이지 입니다.
     *
     * @param cookie 장바구니 쿠키
     * @param model  모델
     * @return 이달의 쿠폰 페이지
     */
    @GetMapping("/coupon-month")
    public String viewCouponMonth(@CookieValue(name = CART, required = false) Cookie cookie,
            Model model) {

        List<GetCouponMonthResponseDto> couponMonthList = couponMonthService.getCouponMonths();

        for (GetCouponMonthResponseDto couponMonth : couponMonthList) {
            if (Boolean.FALSE.equals(redisTemplate.hasKey(COUPON + couponMonth.getTemplateNo()))) {
                redisTemplate.opsForValue()
                        .set(COUPON + couponMonth.getTemplateNo(), couponMonth.getMonthQuantity());
            }
        }

        Long memberNo = memberUtils.getMemberNo();

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);

        model.addAttribute("couponMonthList", couponMonthList);
        model.addAttribute("memberNo", memberNo);

        return "coupon/couponMonth";
    }

}
