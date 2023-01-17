package com.bookpub.bookpubfront.product.relationship.dto.request;

import javax.validation.constraints.NotNull;
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
    @Length(max = 10)
    private String policyMethod;
    @NotNull
    private boolean policySaved;
    private Integer saveRate;
}