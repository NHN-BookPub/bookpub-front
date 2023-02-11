package com.nhnacademy.bookpub.bookpubfront.inquiry.service;

import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 상품문의 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface InquiryService {
    boolean verifyPurchaseProduct(Long memberNo, Long productNo);

    void submitInquiry(Long memberNo, CreateInquiryRequestDto request);

    void cancelInquiryAnswer(Long inquiryNo);

    void inquiryComplete(Long inquiryNo);

    PageResponse<GetInquirySummaryProductResponseDto> getProductInquiryList(
            Pageable pageable, Long productNo);

    PageResponse<GetInquirySummaryMemberResponseDto> getInquiryMemberList(
            Pageable pageable, Long memberNo);

    PageResponse<GetInquirySummaryResponseDto> getInquiryList(Pageable pageable);

    GetInquiryResponseDto getPrivateInquiry(Long inquiryNo);

    GetInquiryResponseDto getPublicInquiry(Long inquiryNo);
}
