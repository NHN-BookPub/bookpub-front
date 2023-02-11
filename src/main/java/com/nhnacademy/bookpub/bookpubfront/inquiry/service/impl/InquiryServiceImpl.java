package com.nhnacademy.bookpub.bookpubfront.inquiry.service.impl;

import com.nhnacademy.bookpub.bookpubfront.inquiry.adaptor.InquiryAdaptor;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 상품문의 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryAdaptor inquiryAdaptor;

    @Override
    public boolean verifyPurchaseProduct(Long memberNo, Long productNo) {
        return inquiryAdaptor.requestVerifyPurchaseProduct(memberNo, productNo);
    }

    @Override
    public void submitInquiry(Long memberNo, CreateInquiryRequestDto request) {
        inquiryAdaptor.requestSubmitInquiry(memberNo, request);
    }

    @Override
    public void cancelInquiryAnswer(Long inquiryNo) {
        inquiryAdaptor.requestCancelInquiryAnswer(inquiryNo);
    }

    @Override
    public void inquiryComplete(Long inquiryNo) {
        inquiryAdaptor.requestInquiryComplete(inquiryNo);
    }

    @Override
    public PageResponse<GetInquirySummaryProductResponseDto> getProductInquiryList(
            Pageable pageable, Long productNo) {
        return inquiryAdaptor.requestProductInquiryList(pageable, productNo);
    }

    @Override
    public PageResponse<GetInquirySummaryMemberResponseDto> getInquiryMemberList(
            Pageable pageable, Long memberNo) {
        return inquiryAdaptor.requestInquiryMemberList(pageable, memberNo);
    }

    @Override
    public PageResponse<GetInquirySummaryResponseDto> getInquiryList(Pageable pageable) {
        return inquiryAdaptor.requestInquiryList(pageable);
    }

    @Override
    public GetInquiryResponseDto getPrivateInquiry(Long inquiryNo) {
        return inquiryAdaptor.requestPrivateInquiry(inquiryNo);
    }

    @Override
    public GetInquiryResponseDto getPublicInquiry(Long inquiryNo) {
        return inquiryAdaptor.requestPublicInquiry(inquiryNo);
    }
}
