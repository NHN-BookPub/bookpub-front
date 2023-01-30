package com.nhnacademy.bookpub.bookpubfront.coupon.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 쿠폰을 등록하기 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateCouponRequestDto {
    @NotBlank(message = "쿠폰템플릿 번호를 입력해주세요.")
    private Long templateNo;

    @NotBlank(message = "멤버 아이디를 입력해주세요.")
    private String memberId;
}
