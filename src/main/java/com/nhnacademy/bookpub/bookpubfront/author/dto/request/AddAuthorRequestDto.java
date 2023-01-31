package com.nhnacademy.bookpub.bookpubfront.author.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 저자 등록을 위한 DTO.
 *
 * @author : 김서현, 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class AddAuthorRequestDto {

    private String authorName;
    private String mainBook;

}
