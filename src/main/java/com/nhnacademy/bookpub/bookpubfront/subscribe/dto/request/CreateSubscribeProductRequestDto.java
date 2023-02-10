package com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 구독에 상품연관관계를 추가할때 쓰임.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateSubscribeProductRequestDto {
    private List<Long> productNo;
}
