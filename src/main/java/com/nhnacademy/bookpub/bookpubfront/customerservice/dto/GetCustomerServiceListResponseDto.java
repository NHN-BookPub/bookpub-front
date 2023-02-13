package com.nhnacademy.bookpub.bookpubfront.customerservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

/**
 * 고객서비스 리스트를 위한 dto 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@AllArgsConstructor
public class GetCustomerServiceListResponseDto {
    private Integer customerServiceNo;
    private String customerServiceStateCode;
    private String memberId;
    private String image;
    private String serviceCategory;
    private String serviceTitle;
    private String serviceContent;
    private LocalDateTime createdAt;
}
