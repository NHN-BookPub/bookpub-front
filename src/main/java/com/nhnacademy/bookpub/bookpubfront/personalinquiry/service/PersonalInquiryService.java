package com.nhnacademy.bookpub.bookpubfront.personalinquiry.service;

import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.request.CreatePersonalInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetPersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetSimplePersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 1대1문의 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface PersonalInquiryService {
    /**
     * 해당 회원의 1대1문의 리스트 조회를 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @return 회원의 1대1문의 리스트 페이징 정보
     */
    PageResponse<GetSimplePersonalInquiryResponseDto> getMemberPersonalInquiries(Pageable pageable);

    /**
     * 1대1문의 리스트 조회를 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @return 1대1문의 리스트 페이징 정보
     */
    PageResponse<GetSimplePersonalInquiryResponseDto> getPersonalInquiries(Pageable pageable);

    /**
     * 1대1문의 단건 조회를 위한 메서드입니다.
     *
     * @param inquiryNo 조회할 1대1문의 번호
     * @return 1대1문의 상세 정보가 담긴 Dto
     */
    GetPersonalInquiryResponseDto getPersonalInquiry(Long inquiryNo);

    /**
     * 1대1문의 생성을 위한 메서드입니다.
     *
     * @param createDto 1대1문의 생성에 필요한 정보를 담은 Dto
     */
    void createPersonalInquiry(CreatePersonalInquiryRequestDto createDto);

    /**
     * 1대1문의 삭제를 위한 메서드입니다.
     *
     * @param inquiryNo 삭제할 1대1문의 번호
     */
    void deletePersonalInquiry(Long inquiryNo);
}
