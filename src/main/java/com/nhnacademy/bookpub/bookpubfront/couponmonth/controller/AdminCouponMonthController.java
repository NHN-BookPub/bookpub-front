package com.nhnacademy.bookpub.bookpubfront.couponmonth.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.CreateCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.ModifyCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.response.GetCouponMonthResponseDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.service.CouponMonthService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지의 이달의 쿠폰을 관리하기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponMonthController {
    private final CouponMonthService couponMonthService;

    /**
     * 이달의 쿠폰 리스트를 조회하는 뷰로 이동하기 위한 메서드입니다.
     *
     * @param model the model
     * @return 이달의 쿠폰 리스트 조회 뷰
     */
    @Auth
    @GetMapping("/coupon/coupon-months")
    public String couponMonthList(Model model) {
        List<GetCouponMonthResponseDto> couponMonthList =
                couponMonthService.getCouponMonths();

        model.addAttribute("couponMonthList", couponMonthList);

        return "admin/coupon/couponMonthPage";
    }

    /**
     * 이달의 쿠폰을 등록하기 위한 메서드입니다.
     *
     * @param createRequestDto 이달의 쿠폰 등록에 필요한 정보를 담은 Dto
     * @return 이달의 쿠폰 리스트 조회 뷰로 redirect
     */
    @Auth
    @PostMapping("/coupon/coupon-months")
    public String addCouponMonth(CreateCouponMonthRequestDto createRequestDto) {
        couponMonthService.createCouponMonth(createRequestDto);

        return "redirect:/admin/coupon/coupon-months";
    }

    /**
     * 이달의 쿠폰을 수정하기 위한 메서드입니다.
     *
     * @param modifyRequestDto 이달의 쿠폰을 수정하기 위한 정보를 담은 Dto
     * @return 이달의 쿠폰 리스트 조회 뷰로 redirect
     */
    @Auth
    @PostMapping("/coupon/coupon-months/modify")
    public String modifyCouponMonth(ModifyCouponMonthRequestDto modifyRequestDto) {
        couponMonthService.modifyCouponMonth(modifyRequestDto);

        return "redirect:/admin/coupon/coupon-months";
    }

    /**
     * 이달의 쿠폰을 삭제하기 위한 메서드입니다.
     *
     * @param monthNo 삭제할 이달의 쿠폰 번호
     * @return 이달의 쿠폰 리스트 조회 뷰로 redirect
     */
    @Auth
    @PostMapping("/coupon/coupon-months/{monthNo}/delete")
    public String deleteCouponMonth(@PathVariable("monthNo") Long monthNo) {
        couponMonthService.deleteCouponMonth(monthNo);

        return "redirect:/admin/coupon/coupon-months";
    }

}
