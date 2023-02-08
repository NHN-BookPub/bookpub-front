package com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 구독정보를 반환하는 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetSubscribeResponseDto {
    private Long subscribeNo;
    private String subscribeName;
    private Long price;
    private Long salePrice;
    private Integer salesRate;
    private Long viewCnt;
    private boolean deleted;
    private boolean renewed;
    private String imagePath;
}
