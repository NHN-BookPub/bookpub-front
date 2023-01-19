package com.bookpub.bookpubfront.coupontemplate.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Some description here.
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
