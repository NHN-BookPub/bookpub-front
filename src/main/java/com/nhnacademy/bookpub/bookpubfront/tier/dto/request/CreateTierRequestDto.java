package com.nhnacademy.bookpub.bookpubfront.tier.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 생성정보를 받기위한 Dto 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateTierRequestDto {
    private String tierName;
    private Integer tierValue;
    private Long tierPrice;
    private Long tierPoint;
}
