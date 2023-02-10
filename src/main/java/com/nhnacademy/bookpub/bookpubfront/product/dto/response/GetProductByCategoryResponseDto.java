package com.nhnacademy.bookpub.bookpubfront.product.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 카테고리를 기준으로 상품을 가져오는 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public class GetProductByCategoryResponseDto {
    private Long productNo;
    private String title;
    private String thumbnail;
    private String ebook;
    private Long salesPrice;
    private Integer salesRate;
    private final List<String> categories = new ArrayList<>();
    private final List<String> authors = new ArrayList<>();
}
