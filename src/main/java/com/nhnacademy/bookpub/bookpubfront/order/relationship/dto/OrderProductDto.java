package com.nhnacademy.bookpub.bookpubfront.order.relationship.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 장바구니에 담겨있는 상품 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class OrderProductDto {
    private Long productNo;
    private String title;
    private Long salesPrice;
    private List<Integer> categoriesNo;
    private int count;
    private long totalPrice;

    /**
     * OrderProductDto의 생성자 입니다.
     *
     * @param productNo  product 번호.
     * @param title      책 제목.
     * @param salesPrice 판매가.
     * @param count      개수.
     */
    @Builder
    public OrderProductDto(Long productNo,
                           String title,
                           Long salesPrice,
                           List<Integer> categoriesNo,
                           int count) {
        this.productNo = productNo;
        this.title = title;
        this.salesPrice = salesPrice;
        this.categoriesNo = categoriesNo;
        this.count = count;
        this.totalPrice = this.salesPrice * this.count;
    }
}
