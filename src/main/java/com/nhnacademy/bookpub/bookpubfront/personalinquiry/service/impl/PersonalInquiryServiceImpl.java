package com.nhnacademy.bookpub.bookpubfront.personalinquiry.service.impl;

import com.nhnacademy.bookpub.bookpubfront.personalinquiry.adaptor.PersonalInquiryAdaptor;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.request.CreatePersonalInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetPersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetSimplePersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.service.PersonalInquiryService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 1대1문의 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class PersonalInquiryServiceImpl implements PersonalInquiryService {
    private final PersonalInquiryAdaptor personalInquiryAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetSimplePersonalInquiryResponseDto> getPersonalInquiries(
            Pageable pageable) {
        return personalInquiryAdaptor.requestPersonalInquiries(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetSimplePersonalInquiryResponseDto> getMemberPersonalInquiries(
            Pageable pageable) {
        return personalInquiryAdaptor.requestMemberPersonalInquiries(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetPersonalInquiryResponseDto getPersonalInquiry(Long inquiryNo) {
        return personalInquiryAdaptor.requestPersonalInquiry(inquiryNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPersonalInquiry(CreatePersonalInquiryRequestDto createDto) {
        personalInquiryAdaptor.requestCreatePersonalInquiry(createDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePersonalInquiry(Long inquiryNo) {
        personalInquiryAdaptor.requestDeletePersonalInquiry(inquiryNo);
    }
}
