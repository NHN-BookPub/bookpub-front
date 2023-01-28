package com.nhnacademy.bookpub.bookpubfront.member.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버별 등급 통계를 반환해주는 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTierStatisticsResponseDto {

    private String tierName;
    private Integer tierValue;
    private Long tierCnt;
}
