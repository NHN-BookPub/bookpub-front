package com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubscribeRequestDto {
    private String name;

    private Long salePrice;
    private Long price;
    private Integer salesRate;
    private Long viewCount;
    private boolean renewed;
}