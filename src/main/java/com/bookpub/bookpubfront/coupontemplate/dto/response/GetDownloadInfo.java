package com.bookpub.bookpubfront.coupontemplate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 오브젝트 스토리지 접근을 위해 정보를 받아오는 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetDownloadInfo {
    private String path;
    private String token;
    private String nameOrigin;
    private String nameSaved;
    private String fileExtension;
}
