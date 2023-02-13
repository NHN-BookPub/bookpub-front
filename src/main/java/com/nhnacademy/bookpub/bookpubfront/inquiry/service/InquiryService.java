package com.nhnacademy.bookpub.bookpubfront.inquiry.service;

import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.CreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 상품문의 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface InquiryService {
    /**
     * 해당 회원이 해당 상품을 구매한 이력이 있는지 확인하기 위한 메서드입니다.
     *
     * @param memberNo  회원 번호
     * @param productNo 상품 번호
     * @return the boolean
     */
    boolean verifyPurchaseProduct(Long memberNo, Long productNo);

    /**
     * 해당 회원이 해당 문의를 조회할 권한이 있는지 확인하기 위한 메서드입니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param memberNo  회원 번호
     * @return the boolean
     */
    boolean verifyCanView(Long inquiryNo, Long memberNo);

    /**
     * 상품문의 등록을 위한 메서드입니다.
     *
     * @param memberNo   회원 번호
     * @param request    상품문의 등록 시 필요한 정보를 담은 dto
     * @param imagePaths 상품문의 등록 시 함께 등록될 이미지 경로들
     */
    void submitInquiry(Long memberNo, CreateInquiryRequestDto request, String imagePaths);

    /**
     * 상품문의 이미지 저장을 위한 메서드입니다.
     * tui editor 적용 시 사용됩니다.
     *
     * @param image 저장할 이미지
     * @return 저장된 이미지 경로
     */
    String addInquiryImage(MultipartFile image);

    /**
     * 해당 상품문의를 삭제하기 위한 메서드입니다.
     * 답변을 삭제할 때 사용됩니다.(자기참조)
     *
     * @param inquiryNo 상품문의 번호
     */
    void cancelInquiryAnswer(Long inquiryNo);

    /**
     * 해당 상품문의를 삭제하기 위한 메서드입니다.
     * 질문을 삭제할 때 사용됩니다.(자기참조)
     * 질문에 달린 답변도 함께 삭제됩니다.
     *
     * @param inquiryNo 상품문의 번호
     */
    void cancelInquiry(Long inquiryNo);

    /**
     * 해당 상품문의의 답변 완료 여부를 수정하기 위한 메서드입니다.
     *
     * @param inquiryNo 수정할 상품문의 번호
     */
    void inquiryComplete(Long inquiryNo);

    /**
     * 해당 상품의 상품문의 간단한 정보 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable  페이징 정보
     * @param productNo 상품 번호
     * @return 문의 리스트 페이징 정보
     */
    PageResponse<GetInquirySummaryProductResponseDto> getProductInquiryList(
            Pageable pageable, Long productNo);

    /**
     * 불량 상품의 상품문의 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @return 불량 상품 문의 리스트 페이징 정보
     */
    PageResponse<GetInquirySummaryResponseDto> getErrorInquiryList(Pageable pageable);

    /**
     * 마이페이지에서의 상품문의 간단한 정보 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @param memberNo 회원 번호
     * @return 마이페이지에서의 상품문의 간단한 정보 리스트 페이징 정보
     */
    PageResponse<GetInquirySummaryMemberResponseDto> getInquiryMemberList(
            Pageable pageable, Long memberNo);

    /**
     * 모든 상품문의의 간단한 정보 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable       페이징 정보
     * @param searchKeyFir   검색 조건
     * @param searchValueFir 검색 값
     * @param searchKeySec   검색 조건 두번째
     * @param searchValueSec 검색 값 두번째
     * @return 모든 상품문의의 간단한 정보 리스트 페이징 정보
     */
    PageResponse<GetInquirySummaryResponseDto> getInquiryList(
            Pageable pageable, String searchKeyFir,
            String searchValueFir, String searchKeySec, String searchValueSec);

    /**
     * 상품문의 단건 조회를 위한 메서드입니다.
     * 비밀 문의글인 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @return 상품문의의 상세 정보를 담은 dto
     */
    GetInquiryResponseDto getPrivateInquiry(Long inquiryNo);

    /**
     * 상품문의 단건 조회를 위한 메서드입니다.
     * 공개 문의글인 경우 사용됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @return 상품문의의 상세 정보를 담은 dto
     */
    GetInquiryResponseDto getPublicInquiry(Long inquiryNo);
}
