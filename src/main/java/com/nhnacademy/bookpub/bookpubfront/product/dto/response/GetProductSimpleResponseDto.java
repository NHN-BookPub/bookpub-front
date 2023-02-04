package com.nhnacademy.bookpub.bookpubfront.product.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetProductSimpleResponseDto {
    private Long productNo;
    private String title;
    private String productIsbn;
    private String productPublisher;
    private List<String> productAuthorNames = new ArrayList<>();
    private String productImagePath;
}
