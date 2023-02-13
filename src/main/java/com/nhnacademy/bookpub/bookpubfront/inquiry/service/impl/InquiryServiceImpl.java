package com.nhnacademy.bookpub.bookpubfront.inquiry.service.impl;

import com.nhnacademy.bookpub.bookpubfront.inquiry.adaptor.InquiryAdaptor;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final MemberUtils memberUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyPurchaseProduct(Long memberNo, Long productNo) {
        return inquiryAdaptor.requestVerifyPurchaseProduct(memberNo, productNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyCanView(Long inquiryNo, Long memberNo) {
        return inquiryAdaptor.requestVerifyCanView(inquiryNo, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void submitInquiry(Long memberNo, CreateInquiryRequestDto request, String imagePaths) {
        List<String> resultImagePaths = new ArrayList<>();
        if (Objects.nonNull(imagePaths)) {
            resultImagePaths = Arrays.asList(imagePaths.split("\\$"));
        }

        inquiryAdaptor.requestSubmitInquiry(memberNo, request.transform(resultImagePaths));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addInquiryImage(MultipartFile image) {
        Long memberNo = memberUtils.getMemberNo();
        return inquiryAdaptor.requestSaveInquiryImage(memberNo, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelInquiryAnswer(Long inquiryNo) {
        inquiryAdaptor.requestCancelInquiryAnswer(inquiryNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelInquiry(Long inquiryNo) {
        Long memberNo = memberUtils.getMemberNo();
        inquiryAdaptor.requestCancelInquiry(inquiryNo, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inquiryComplete(Long inquiryNo) {
        inquiryAdaptor.requestInquiryComplete(inquiryNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetInquirySummaryProductResponseDto> getProductInquiryList(
            Pageable pageable, Long productNo) {
        return inquiryAdaptor.requestProductInquiryList(pageable, productNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetInquirySummaryMemberResponseDto> getInquiryMemberList(
            Pageable pageable, Long memberNo) {
        return inquiryAdaptor.requestInquiryMemberList(pageable, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetInquirySummaryResponseDto> getErrorInquiryList(Pageable pageable) {
        return inquiryAdaptor.requestErrorInquiryList(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetInquirySummaryResponseDto> getInquiryList(
            Pageable pageable, String searchKeyFir,
            String searchValueFir, String searchKeySec, String searchValueSec) {
        return inquiryAdaptor.requestInquiryList(pageable,
                searchKeyFir, searchValueFir, searchKeySec, searchValueSec);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetInquiryResponseDto getPrivateInquiry(Long inquiryNo) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        return inquiryAdaptor.requestPrivateInquiry(inquiryNo, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetInquiryResponseDto getPublicInquiry(Long inquiryNo) {
        return inquiryAdaptor.requestPublicInquiry(inquiryNo);
    }
}
