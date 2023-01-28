package com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 쿠폰템플릿 파일 정보를 전송하기 위해 변환한 정보를 담은 Dto.
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
    private LocalDateTime finishedAt;
    private boolean templateBundled;
}
