package com.nhnacademy.bookpub.bookpubfront.file.service.impl;

import com.nhnacademy.bookpub.bookpubfront.file.adaptor.FileAdaptor;
import com.nhnacademy.bookpub.bookpubfront.file.dto.response.GetDownloadInfo;
import com.nhnacademy.bookpub.bookpubfront.file.service.FileService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * FileService 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileAdaptor fileAdaptor;
    private final RestTemplate restTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDownloadInfo downloadCouponTemplateInfo(Long templateNo) {
        return fileAdaptor.requestCouponTemplateInfo(templateNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDownloadInfo downloadEBookInfo(Long productNo, Long memberNo) {
        return fileAdaptor.requestEBookInfo(productNo, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] downloadFile(String path, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        ResponseEntity<byte[]> response = restTemplate.exchange(
                path,
                HttpMethod.GET,
                new HttpEntity<String>(null, headers),
                byte[].class
        );

        return response.getBody();
    }
}
