package com.nhnacademy.bookpub.bookpubfront.utils;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 응답받을 페이징 객체입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;

    private int totalPages;

    private int number;

    private boolean previous;

    private boolean next;
}
