package com.nhnacademy.bookpub.bookpubfront.order.relationship.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문상품상태코드를 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class CreateOrderProductStateCodeRequestDto {
    private String codeName;
    private boolean codeUsed;
    private String codeInfo;
}
