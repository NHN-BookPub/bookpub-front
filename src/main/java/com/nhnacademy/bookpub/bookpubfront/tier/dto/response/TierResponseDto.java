package com.nhnacademy.bookpub.bookpubfront.tier.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 등급의 대한 정보를 반환받기위한 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class TierResponseDto {
    private Integer tierNo;
    private String tierName;
    private Integer tierValue;
    private Long tierPrice;
    private Long tierPoint;
}
