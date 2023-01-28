package com.nhnacademy.bookpub.bookpubfront.order.relationship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주문상품상태코드를 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class GetOrderProductStateCodeResponseDto {
    private Integer codeNo;
    private String codeName;
    private boolean codeUsed;
    private String codeInfo;
}
