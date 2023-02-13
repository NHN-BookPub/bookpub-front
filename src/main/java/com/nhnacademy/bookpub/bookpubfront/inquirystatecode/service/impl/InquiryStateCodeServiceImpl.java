package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service.impl;

import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.adaptor.InquiryStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response.GetInquiryStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service.InquiryStateCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 상품문의상태코드 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class InquiryStateCodeServiceImpl implements InquiryStateCodeService {
    private final InquiryStateCodeAdaptor inquiryStateCodeAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetInquiryStateCodeResponseDto> getInquiryStateCodeListForMember() {
        return inquiryStateCodeAdaptor.requestInquiryStateCodeForMember();
    }
}
