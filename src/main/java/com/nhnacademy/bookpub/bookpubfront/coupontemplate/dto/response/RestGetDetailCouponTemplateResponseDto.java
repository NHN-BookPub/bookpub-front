package com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * front로 파일을 보내기 위해 변환한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestGetDetailCouponTemplateResponseDto {
    private Long templateNo;
    private boolean policyFixed;
    private Long policyPrice;
    private Long policyMinimum;
    private Long maxDiscount;
    private String typeName;
    private String productTitle;
    private String categoryName;
    private String codeTarget;
    private String templateName;
    private String templateImage;
    private LocalDateTime finishedAt;
    private boolean templateBundled;
}
