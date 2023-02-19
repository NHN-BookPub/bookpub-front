package com.nhnacademy.bookpub.bookpubfront.elastic.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 통합검색 결과를 받을 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class AllSearchResponseDto {
    private Long id;
    private String title;
    private Long salesPrice;
    private Integer salesRate;
    private String filePath;
    private Integer csId;
    private String csCodeName;
    private String csTitle;
    private String csCategory;
    private LocalDateTime csDate;
}
