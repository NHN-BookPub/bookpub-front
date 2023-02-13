package com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품등록 시 이미지를 추가하기 위해 변환한 dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class RestCreateInquiryRequestDto {
    private Long inquiryParentNo;
    private Long productNo;
    private Integer inquiryStateCodeNo;
    private String inquiryTitle;
    private String inquiryContent;
    private boolean inquiryDisplayed;
    private List<String> imagePaths;
}
