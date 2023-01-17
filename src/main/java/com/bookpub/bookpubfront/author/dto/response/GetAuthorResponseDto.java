package com.bookpub.bookpubfront.author.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 저자 조회을 위한 DTO.
 *
 * @author : 박경서, 정유진
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetAuthorResponseDto {
    private Integer authorNo;
    private String authorName;
}
