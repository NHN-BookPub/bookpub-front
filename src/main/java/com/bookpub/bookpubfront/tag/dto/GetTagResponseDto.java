package com.bookpub.bookpubfront.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Some description here.
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
