package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service;

import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response.GetInquiryStateCodeResponseDto;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface InquiryStateCodeService {

    List<GetInquiryStateCodeResponseDto> getInquiryStateCodeListForMember();
}