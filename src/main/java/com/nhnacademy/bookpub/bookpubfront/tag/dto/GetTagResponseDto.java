package com.nhnacademy.bookpub.bookpubfront.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 태그 조회를 위한 DTO.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetTagResponseDto {
    private Integer tagNo;
    private String tagName;
    private String colorCode;
}
