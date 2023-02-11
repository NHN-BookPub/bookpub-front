package com.nhnacademy.bookpub.bookpubfront.file.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 파일 다운로드 정보.
 *
 * @author : 박경서
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