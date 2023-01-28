package com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request;

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
    private boolean templateBundled;

    /**
     * 이미지를 제외한 Dto로 변환해주는 메서드입니다.
     *
     * @return 서버간 전송을 위해 이미지를 제외한 Dto
     */
    public RestCouponTemplateRequestDto transform() {
        return new RestCouponTemplateRequestDto(
                this.getPolicyNo(), this.getTypeNo(), this.getProductNo(), this.getCategoryNo(),
                this.getCodeNo(), this.getTemplateName(),
                this.getFinishedAt(), this.isTemplateBundled());
    }
}
