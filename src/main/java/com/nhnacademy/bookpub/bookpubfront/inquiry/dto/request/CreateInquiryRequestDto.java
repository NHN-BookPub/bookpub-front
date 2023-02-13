package com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품문의 등록을 위한 정보를 담은 dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateInquiryRequestDto {
    private Long inquiryParentNo;
    private Long productNo;
    private Integer inquiryStateCodeNo;
    private String inquiryTitle;
    private String inquiryContent;
    private boolean inquiryDisplayed;

    /**
     * shop 서버로 보내기 위해 변환한 dto.
     * 이미지의 경로를 추가하여 새로운 dto를 만들어냅니다.
     *
     * @param imagePaths 이미지 경로 리스트
     * @return 이미지의 경로가 추가된 dto
     */
    public RestCreateInquiryRequestDto transform(List<String> imagePaths) {
        return new RestCreateInquiryRequestDto(
                this.inquiryParentNo,
                this.productNo,
                this.inquiryStateCodeNo,
                this.inquiryTitle,
                this.getInquiryContent(),
                this.inquiryDisplayed,
                imagePaths
        );
    }
}
