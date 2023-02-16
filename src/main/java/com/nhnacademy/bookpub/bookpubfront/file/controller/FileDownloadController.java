package com.nhnacademy.bookpub.bookpubfront.file.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.file.dto.response.GetDownloadInfo;
import com.nhnacademy.bookpub.bookpubfront.file.service.FileService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 파일(이미지, E-Book) 다운을 위한 controller.
 *
 * @author : 정유진, 박경서
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class FileDownloadController {
    private final FileService fileService;
    private final MemberUtils memberUtils;

    /**
     * 파일을 다운로드하기 위한 메서드입니다.
     *
     * @param templateNo 템플릿 번호
     * @return 다운 받아질 Resource
     */
    @Auth
    @GetMapping("/download/coupon-templates/{templateNo}")
    public ResponseEntity<Resource> downloadCouponTemplateFile(@PathVariable Long templateNo) {
        GetDownloadInfo info = fileService.downloadCouponTemplateInfo(templateNo);
        byte[] file = fileService.downloadFile(info.getPath(), info.getToken());

        ByteArrayResource resource = new ByteArrayResource(file);

        String contentDisposition = "attachment; filename=\"" + info.getNameOrigin() + info.getFileExtension() + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    /**
     * E-Book 다운로드를 위한 메서드.
     *
     * @param productNo 상품 번호
     * @return E-Book(pdf) 파일
     */
    @Auth
    @GetMapping("/download/e-book/{productNo}")
    public ResponseEntity<Resource> downloadEBookFile(@PathVariable("productNo") Long productNo) {
        Long memberNo = memberUtils.getMemberNo();
        GetDownloadInfo info = fileService.downloadEBookInfo(productNo, memberNo);
        byte[] file = fileService.downloadFile(info.getPath(), info.getToken());

        ByteArrayResource resource = new ByteArrayResource(file);
        String contentDisposition = "attachment; filename=\"" + info.getNameOrigin() + info.getFileExtension() + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
