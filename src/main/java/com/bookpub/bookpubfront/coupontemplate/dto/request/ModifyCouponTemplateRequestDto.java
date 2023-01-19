package com.bookpub.bookpubfront.coupontemplate.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * 쿠폰템플릿을 수정하기 위한 정보를 담은 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyCouponTemplateRequestDto {
    @NotNull(message = "수정할 쿠폰템플릿 번호를 기입해주세요.")
    private Long templateNo;

    @NotNull(message = "정책번호를 기입해주세요.")
    private Integer policyNo;

    @NotNull(message = "유형번호를 기입해주세요.")
    private Long typeNo;

    private Long productNo;

    private Integer categoryNo;

    @NotNull(message = "상태번호를 기입해주세요.")
    private Integer codeNo;

    @NotBlank(message = "쿠폰이름을 기입해주세요.")
    @Length(max = 50, message = "쿠폰이름의 최대 글자는 50글자입니다.")
    private String templateName;

    private MultipartFile templateImage;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime finishedAt;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime issuedAt;

    @NotNull
    private boolean templateOverlapped;

    private boolean templateBundled;

    public RestCouponTemplateRequestDto transform() {
        return new RestCouponTemplateRequestDto(
                this.getPolicyNo(), this.getTypeNo(), this.getProductNo(), this.getCategoryNo(),
                this.getCodeNo(), this.getTemplateName(),
                this.getFinishedAt(), this.getIssuedAt(), this.isTemplateOverlapped(), this.isTemplateBundled());
    }
}
