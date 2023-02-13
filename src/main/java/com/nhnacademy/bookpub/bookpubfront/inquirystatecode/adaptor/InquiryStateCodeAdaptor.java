package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.adaptor;

import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response.GetInquiryStateCodeResponseDto;
import java.util.List;

/**
 * 상품문의상태코드 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface InquiryStateCodeAdaptor {
    /**
     * 회원을 위한 상품문의상태코드 리스트를 조회하기 위한 메서드입니다.
     *
     * @return 상품문의상태코드 정보를 담은 dto 리스트
     */
    List<GetInquiryStateCodeResponseDto> requestInquiryStateCodeForMember();
}
