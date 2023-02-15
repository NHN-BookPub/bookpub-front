package com.nhnacademy.bookpub.bookpubfront.personalinquiry.adaptor;

import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.request.CreatePersonalInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetPersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetSimplePersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * shop 서버와 통신하기 위한 1대1문의 어댑터입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface PersonalInquiryAdaptor {
    /**
     * 해당 회원의 1대1문의 리스트가 담긴 페이징 정보를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @return the page response
     */
    PageResponse<GetSimplePersonalInquiryResponseDto> requestMemberPersonalInquiries(
            Pageable pageable);

    /**
     * 1대1문의 리스트가 담긴 페이징 정보를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @return the page response
     */
    PageResponse<GetSimplePersonalInquiryResponseDto> requestPersonalInquiries(Pageable pageable);

    /**
     * 1대1문의 단건 상세 조회를 위한 메서드입니다.
     *
     * @param inquiryNo 조회할 1대1문의 번호
     * @return 1대1문의 정보가 담긴 dto
     */
    GetPersonalInquiryResponseDto requestPersonalInquiry(Long inquiryNo);

    /**
     * 1대1문의 생성을 위한 메서드입니다.
     *
     * @param createDto 1대1문의 생성 시 필요한 정보를 담은 dto
     */
    void requestCreatePersonalInquiry(CreatePersonalInquiryRequestDto createDto);

    /**
     * 1대1문의 삭제를 위한 메서드입니다.
     *
     * @param inquiryNo 삭제할 1대1문의 번호
     */
    void requestDeletePersonalInquiry(Long inquiryNo);
}
