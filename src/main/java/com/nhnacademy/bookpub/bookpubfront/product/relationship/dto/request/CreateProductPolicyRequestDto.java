package com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 상품 정책 등록을 위한 DTO.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateProductPolicyRequestDto {
    @Length(min = 1, max = 10)
    private String policyMethod;
    @NotNull
    private boolean policySaved;
    @PositiveOrZero
    private Integer saveRate;
}