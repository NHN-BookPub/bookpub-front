package com.bookpub.bookpubfront.product.dto.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

/**
 * 상품 상세 정보를 담는 DTO.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public class GetProductDetailResponseDto {
    private Long productNo;
    private String productIsbn;
    private String title;
    private Integer pageCount;
    private String productDescription;
    private Long salesPrice;
    private Long productPrice;
    private Integer salesRate;
    private Integer productPriority;
    private Integer productStock;
    private LocalDateTime publishDate;
    private boolean deleted;
    private boolean productSubscribed;
    private String saleStateCodeCategory;
    private String typeStateCodeName;

    // TODO: 매입이력 추가

    private String policyMethod;
    private boolean policySaved;
    private Integer policySaveRate;

    private Set<String> authors = new HashSet<>();
    private Set<String> categories = new HashSet<>();
    private Set<String> tags = new HashSet<>();
    private Set<String> tagsColors = new HashSet<>();
}
