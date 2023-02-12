package com.nhnacademy.bookpub.bookpubfront.inquiry.adaptor;

import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * shop 서버와 통신하기 위한 상품문의 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
public interface InquiryAdaptor {
    boolean requestVerifyPurchaseProduct(Long memberNo, Long productNo);

    void requestSubmitInquiry(Long memberNo, CreateInquiryRequestDto request);

    void requestCancelInquiryAnswer(Long inquiryNo);

    void requestInquiryComplete(Long inquiryNo);

    PageResponse<GetInquirySummaryProductResponseDto> requestProductInquiryList(
            Pageable pageable, Long productNo);

    PageResponse<GetInquirySummaryMemberResponseDto> requestInquiryMemberList(
            Pageable pageable, Long memberNo);

    PageResponse<GetInquirySummaryResponseDto> requestInquiryList(Pageable pageable);

    GetInquiryResponseDto requestPrivateInquiry(Long inquiryNo);

    GetInquiryResponseDto requestPublicInquiry(Long inquiryNo);
}
