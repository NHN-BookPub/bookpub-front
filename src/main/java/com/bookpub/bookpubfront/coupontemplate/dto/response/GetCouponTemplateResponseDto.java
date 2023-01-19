package com.bookpub.bookpubfront.coupontemplate.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

/**
 * Some description here.
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
