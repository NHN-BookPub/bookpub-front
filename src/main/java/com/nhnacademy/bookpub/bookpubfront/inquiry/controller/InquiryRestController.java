package com.nhnacademy.bookpub.bookpubfront.inquiry.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class InquiryRestController {
    private final InquiryService inquiryService;

    @Auth
    @PostMapping
    public void saveInquiryImage() {

    }

    @GetMapping("/inquiries/products/{productNo}")
    public PageResponse<GetInquirySummaryProductResponseDto> ProductInquiryList(
            @PathVariable Long productNo,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) {
        return inquiryService.getProductInquiryList(PageRequest.of(page, size), productNo);
    }
}
