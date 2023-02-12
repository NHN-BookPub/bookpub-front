package com.nhnacademy.bookpub.bookpubfront.product.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private String thumbnail;
    private String detail;
    private String ebook;
    private String productPublisher;
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
    private String policyMethod;
    private boolean policySaved;
    private Integer policySaveRate;

    private List<String> authors = new ArrayList<>();
    private List<Integer> categoriesNo = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private List<String> tagsColors = new ArrayList<>();
    private List<GetRelationProductInfoResponseDto> info = new ArrayList<>();
}
