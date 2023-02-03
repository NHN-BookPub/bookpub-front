package com.nhnacademy.bookpub.bookpubfront.main.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 상품 유형을 기준으로 상품을 조회받는 DTO.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public class GetProductByTypeResponseDto {
    private Long productNo;
    private String title;
    private String thumbnail;
    private Long salesPrice;
    private Long productPrice;
    private Integer salesRate;
    private List<String> productCategories = new ArrayList<>();
}
