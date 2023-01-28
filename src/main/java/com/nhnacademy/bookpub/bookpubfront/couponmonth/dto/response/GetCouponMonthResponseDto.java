package com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 이달의 쿠폰 조회를 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetCouponMonthResponseDto {
    private Long monthNo;
    private Long templateNo;
    private String templateName;
    private String templateImage;
    private LocalDateTime openedAt;
    private Integer monthQuantity;
}
