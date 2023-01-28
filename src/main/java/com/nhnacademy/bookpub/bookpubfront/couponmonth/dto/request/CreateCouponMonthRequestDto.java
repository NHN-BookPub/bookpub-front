package com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 이달의 쿠폰 등록에 필요한 정보를 담은 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateCouponMonthRequestDto {
    private Long templateNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime openedAt;

    private Integer monthQuantity;
}
