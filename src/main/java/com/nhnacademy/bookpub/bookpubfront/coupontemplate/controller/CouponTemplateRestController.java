package com.nhnacademy.bookpub.bookpubfront.coupontemplate.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.service.CouponTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 쿠폰템플릿 관련 정보 조회를 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class CouponTemplateRestController {
    private final CouponTemplateService couponTemplateService;

    /**
     * 쿠폰 템플릿이 존재하는지 확인하기 위한 메서드입니다.
     *
     * @param templateNo 확인할 쿠폰템플릿 번호
     * @return the boolean
     */
    @Auth
    @GetMapping(value = "/templateCheck")
    public boolean existTemplateCheck(@RequestParam("templateNo") Long templateNo) {
        return couponTemplateService.existTemplateCheck(templateNo);
    }
}
