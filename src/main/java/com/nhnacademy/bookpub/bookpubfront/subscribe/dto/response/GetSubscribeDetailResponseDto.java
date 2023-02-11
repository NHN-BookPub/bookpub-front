package com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 구독에 대한 상세내용을 받기위한 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetSubscribeDetailResponseDto {
    private Long subscribeNo;
    private String subscribeName;
    private Long price;
    private Long salePrice;
    private Integer salesRate;
    private Long viewCnt;
    private boolean deleted;
    private boolean renewed;
    private String imagePath;

    private List<GetSubscribeProductListDto> productLists;
}
