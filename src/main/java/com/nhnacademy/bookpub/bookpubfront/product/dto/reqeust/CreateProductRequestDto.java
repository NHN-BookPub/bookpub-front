package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API 에게 보낼 상품 등록을 위한 DTO.
 *
 * @author : 박경서, 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateProductRequestDto {
    private String productIsbn;
    private String title;
    private String productPublisher;
    private Integer pageCount;
    private String productDescription;
    private Long salePrice;
    private Long productPrice;
    private Integer salesRate;
    private Integer productPriority;
    private Integer productStock;
    private LocalDateTime publishedAt;
    private boolean subscribed;
    private Integer productPolicyNo;
    private Integer saleCodeNo;
    private Integer typeCodeNo;
    private List<Integer> authorsNo;
    private List<Integer> categoriesNo;
    private List<Integer> tagsNo;
}
