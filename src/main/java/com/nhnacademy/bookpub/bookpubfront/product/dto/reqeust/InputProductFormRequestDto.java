package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 상품 등록 모달창에서 입력 받는 값을 담는 DTO.
 *
 * @author : 박경서, 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class InputProductFormRequestDto {
    private Integer typeStateNo;
    private Integer saleStateNo;
    private Integer policyNo;
    private String title;
    private String productIsbn;
    private String authors;
    private String categoryOne;
    private String categoryTwo;
    private String categoryThree;
    private String productPublisher;
    private Integer pageCount;
    private String productDescription;
    private Long productPrice;
    private Integer saleRate;
    private Long salePrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime publishedAt;
    private Integer priority;
    private String subscribed;

    public void setSalePrice(Long productPrice, Integer saleRate) {
        Double tmp = productPrice * (100 - saleRate) * 0.01;
        this.salePrice = tmp.longValue();
    }
}
