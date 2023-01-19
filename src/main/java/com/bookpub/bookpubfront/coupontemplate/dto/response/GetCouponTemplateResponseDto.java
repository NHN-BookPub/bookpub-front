package com.bookpub.bookpubfront.coupontemplate.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

/**
 * 쿠폰템플릿을 조회하기 위한 정보를 담은 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@ToString
public class GetCouponTemplateResponseDto {
    private Long templateNo;
    private String templateName;
    private String templateImage;
    private LocalDateTime issuedAt;
    private LocalDateTime finishedAt;
}
