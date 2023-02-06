package com.nhnacademy.bookpub.bookpubfront.review.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 마이페이지에서 회원의 상품평들을 조회하기 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetMemberReviewResponseDto {
    private Long reviewNo;
    private Long productNo;
    private String productTitle;
    private String productPublisher;
    private List<String> productAuthorNames = new ArrayList<>();
    private String productImagePath;
    private Integer reviewStar;
    private String reviewContent;
    private String reviewImagePath;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;
}
