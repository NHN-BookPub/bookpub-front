package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service.impl;

import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.adaptor.InquiryStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response.GetInquiryStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service.InquiryStateCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class InquiryStateCodeServiceImpl implements InquiryStateCodeService {
    private final InquiryStateCodeAdaptor inquiryStateCodeAdaptor;

    @Override
    public List<GetInquiryStateCodeResponseDto> getInquiryStateCodeListForMember() {
        return inquiryStateCodeAdaptor.requestInquiryStateCodeForMember();
    }
}
