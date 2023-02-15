package com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 1대1문의 간단한 정보를 담을 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetSimplePersonalInquiryResponseDto {
    private Long inquiryNo;
    private String memberNickname;
    private String inquiryTitle;
    private boolean inquiryAnswered;
    private LocalDateTime createdAt;
}
