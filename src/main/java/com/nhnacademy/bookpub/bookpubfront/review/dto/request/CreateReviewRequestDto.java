package com.nhnacademy.bookpub.bookpubfront.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 상품평 등록을 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateReviewRequestDto {
    private Long productNo;
    private Integer reviewStar;
    private String reviewContent;
    private MultipartFile reviewImage;

    /**
     * shop서버로 등록할 상품평 정보를 보내기 위해 변환해주는 메서드입니다.
     * 이미지를 제외한 나머지 정보들이 들어가고, 회원정보가 추가로 들어갑니다.
     *
     * @param memberNo 회원 번호
     * @return shop서버와 통신하기 위한 Dto.
     */
    public RestCreateReviewRequestDto transform(Long memberNo) {
        return RestCreateReviewRequestDto.builder()
                .request(this)
                .memberNo(memberNo)
                .build();
    }
}
