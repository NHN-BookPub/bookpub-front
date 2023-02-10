package com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 구독안에 연관상품의 정보를 받기위한 dto 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetSubscribeProductListDto {
    private Long productNo;
    private String title;
    private String filePath;
}
