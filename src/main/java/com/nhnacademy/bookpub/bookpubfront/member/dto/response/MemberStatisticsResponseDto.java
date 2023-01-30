package com.nhnacademy.bookpub.bookpubfront.member.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원의 통계를 보여주는 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberStatisticsResponseDto {
    private Long memberCnt;

    private Long currentMemberCnt;

    private Long deleteMemberCnt;

    private Long blockMemberCnt;
}
