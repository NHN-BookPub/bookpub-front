package com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateSubscribeRequestDto {
    private String name;
    private Long salePrice;
    private Long price;
    private Integer salesRate;
    private boolean renewed;
}