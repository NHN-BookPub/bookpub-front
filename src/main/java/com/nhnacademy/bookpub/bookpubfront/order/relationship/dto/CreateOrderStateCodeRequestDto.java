package com.nhnacademy.bookpub.bookpubfront.order.relationship.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 주문상태코드 생성 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class CreateOrderStateCodeRequestDto {
    @Length(max = 20)
    private String codeName;
    @NotNull
    private boolean codeUsed;
    @Length(max = 100)
    private String codeInfo;
}
