package com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 모든 상품문의 간단한 정보 조회를 위한 dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetInquirySummaryResponseDto {
    private Long inquiryNo;
    private Long productNo;
    private Long memberNo;
    private String inquiryStateCodeName;
    private String memberNickname;
    private String productTitle;
    private String productIsbn;
    private List<String> productCategories = new ArrayList<>();
    private String inquiryTitle;
    private boolean inquiryDisplayed;
    private boolean inquiryAnswered;
    private LocalDateTime createdAt;
}
