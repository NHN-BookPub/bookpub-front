package com.bookpub.bookpubfront.coupontemplate.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetDetailCouponTemplateResponseDto {
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
    private LocalDateTime issuedAt;
    private LocalDateTime finishedAt;
    private boolean templateOverlapped;
    private boolean templateBundled;
}
