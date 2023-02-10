package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 상품 수정을 위한 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyProductInfoRequestDto {
    private String productIsbn;
    private Long productPrice;
    private Integer salesRate;
    private String productPublisher;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime publishedAt;
    private Integer pageCount;
    private Long salesPrice;
    private Integer priority;

    /**
     * 판매가를 세팅하는 메서드.
     *
     * @param productPrice 상품 정가 가격
     * @param saleRate     할인율
     */
    public void setSalePrice(Long productPrice, Integer saleRate) {
        Double tmp = productPrice * (100 - saleRate) * 0.01;
        this.salesPrice = tmp.longValue();
    }
}
