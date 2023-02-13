package com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 상품문의 상세 조회를 위한 dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetInquiryResponseDto {
    private Long inquiryNo;
    private Long memberNo;
    private Long productNo;
    private String inquiryStateCodeName;
    private String memberNickname;
    private String productTitle;
    private String inquiryTitle;
    private String inquiryContent;
    private boolean inquiryDisplayed;
    private boolean inquiryAnswered;
    private List<GetInquiryResponseDto> childInquiries = new ArrayList<>();
    private LocalDateTime createdAt;
}
