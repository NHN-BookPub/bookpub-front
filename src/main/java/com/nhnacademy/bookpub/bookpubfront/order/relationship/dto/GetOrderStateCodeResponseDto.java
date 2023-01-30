package com.nhnacademy.bookpub.bookpubfront.order.relationship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주문상태코드 조회를 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class GetOrderStateCodeResponseDto {
    private Integer codeNo;
    private String codeName;
    private boolean codeUsed;
    private String codeInfo;
}
