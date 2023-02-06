package com.nhnacademy.bookpub.bookpubfront.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 상품평 수정을 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyReviewRequestDto {
    private Integer reviewStar;
    private String reviewContent;
    private MultipartFile reviewImage;


    /**
     * shop서버로 상품평 수정 정보를 보내기 위해 변환해주는 메서드입니다.
     * 이미지를 제외한 정보가 들어갑니다.
     *
     * @return shop서버와 통신하기 위해 변환된 Dto
     */
    public RestModifyReviewRequestDto transform() {
        return RestModifyReviewRequestDto.builder()
                .request(this)
                .build();
    }
}
