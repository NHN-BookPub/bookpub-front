package com.nhnacademy.bookpub.bookpubfront.point.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 포인트 선물 request dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@AllArgsConstructor
@Getter
public class PointGiftRequestDto {
    @NotNull
    private String nickname;
    @NotNull
    private Long pointAmount;
}
