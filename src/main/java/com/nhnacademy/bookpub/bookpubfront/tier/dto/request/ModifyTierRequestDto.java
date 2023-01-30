package com.nhnacademy.bookpub.bookpubfront.tier.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 등급수정을 하기위한 요청 dto 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/

@Getter
@AllArgsConstructor
public class ModifyTierRequestDto {
    private Integer tierNo;
    private String tierName;
}
