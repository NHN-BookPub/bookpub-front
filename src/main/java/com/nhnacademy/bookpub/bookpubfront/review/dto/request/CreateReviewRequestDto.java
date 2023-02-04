package com.nhnacademy.bookpub.bookpubfront.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateReviewRequestDto {
    private Long productNo;
    private Long reviewStar;
    private String reviewContent;
    private MultipartFile reviewImage;

    public RestCreateReviewRequestDto transform(Long memberNo) {
        return RestCreateReviewRequestDto.builder()
                .request(this)
                .memberNo(memberNo)
                .build();
    }
}
