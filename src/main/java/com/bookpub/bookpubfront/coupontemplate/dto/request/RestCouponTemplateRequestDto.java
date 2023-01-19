package com.bookpub.bookpubfront.coupontemplate.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class RestCouponTemplateRequestDto {
    private Integer policyNo;
    private Long typeNo;
    private Long productNo;
    private Integer categoryNo;
    private Integer codeNo;
    private String templateName;
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime finishedAt;
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime issuedAt;
    private boolean templateOverlapped;
    private boolean templateBundled;
}
