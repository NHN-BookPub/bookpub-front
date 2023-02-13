package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.service;

import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response.GetInquiryStateCodeResponseDto;
import java.util.List;

/**
 * 상품문의상태코드 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface InquiryStateCodeService {

    /**
     * 회원들이 사용할 수 있는 상품문의상태코드 리스트 조회를 위한 메서드입니다.
     *
     * @return 상품문의상태코드 정보를 담은 dto 리스트
     */
    List<GetInquiryStateCodeResponseDto> getInquiryStateCodeListForMember();
}
