package com.bookpub.bookpubfront.coupontemplate.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * 쿠폰템플릿을 등록하기 위한 정보를 담은 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/

@Getter
@AllArgsConstructor
public class CreateCouponTemplateRequestDto {
    @NotNull
    private Integer policyNo;
    @NotNull
    private Long typeNo;
    private Long productNo;
    private Integer categoryNo;
    @NotNull
    private Integer codeNo;
    @NotNull
    private String templateName;
    private MultipartFile templateImage;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime finishedAt;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime issuedAt;
    @NotNull
    private boolean templateOverlapped;
    @NotNull
    private boolean templateBundled;

    public RestCouponTemplateRequestDto transform() {
        return new RestCouponTemplateRequestDto(
                this.getPolicyNo(), this.getTypeNo(), this.getProductNo(), this.getCategoryNo(),
                this.getCodeNo(), this.getTemplateName(),
                this.getFinishedAt(), this.getIssuedAt(), this.isTemplateOverlapped(), this.isTemplateBundled());
    }
}
