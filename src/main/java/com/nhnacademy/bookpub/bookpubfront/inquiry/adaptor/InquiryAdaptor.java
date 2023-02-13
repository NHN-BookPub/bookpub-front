package com.nhnacademy.bookpub.bookpubfront.inquiry.adaptor;

import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.RestCreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * shop 서버와 통신하기 위한 상품문의 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface InquiryAdaptor {
    /**
     * 해당 회원이 해당 상품을 구매한 이력이 있는지 확인하기 위한 메서드입니다.
     * 구매한 이력이 있다면 true, 없으면 false 를 반환합니다.
     *
     * @param memberNo  회원 번호
     * @param productNo 상품 번호
     * @return the boolean
     */
    boolean requestVerifyPurchaseProduct(Long memberNo, Long productNo);

    /**
     * 해당 회원이 해당 상품문의를 조회할 권한이 있는지 확인하기 위한 메서드입니다.
     * 비밀글일 경우 해당 글의 작성자와 관리자만 true 가 반환됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param memberNo  회원 번호
     * @return the boolean
     */
    boolean requestVerifyCanView(Long inquiryNo, Long memberNo);

    /**
     * 상품문의 등록을 위한 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @param request  상품문의 등록을 위한 정보를 담은 dto
     */
    void requestSubmitInquiry(Long memberNo, RestCreateInquiryRequestDto request);

    /**
     * 상품문의의 이미지를 저장하기 위한 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @param image    저장할 이미지
     * @return 저장된 이미지 경로
     */
    String requestSaveInquiryImage(Long memberNo, MultipartFile image);

    /**
     * 상품문의의 답변을 삭제하기 위한 메서드입니다.
     *
     * @param inquiryNo 상품문의 번호
     */
    void requestCancelInquiryAnswer(Long inquiryNo);

    /**
     * 상품문의를 삭제하기 위한 메서드입니다.
     * 상품문의가 삭제될 경우, 관련된 상품문의 답변도 삭제됩니다.
     *
     * @param inquiryNo 상품문의 번호
     * @param memberNo  회원 번호
     */
    void requestCancelInquiry(Long inquiryNo, Long memberNo);

    /**
     * 상품문의 답변 완료 여부를 수정하기 위한 메서드입니다.
     *
     * @param inquiryNo 수저할 상품문의 번호
     */
    void requestInquiryComplete(Long inquiryNo);

    /**
     * 해당 상품의 상품문의의 간단한 정보 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable  페이징 정보
     * @param productNo 상품 번호
     * @return 상품문의의 간단한 정보 리스트 페이징 정보
     */
    PageResponse<GetInquirySummaryProductResponseDto> requestProductInquiryList(
            Pageable pageable, Long productNo);

    /**
     * 해당 회원이 작성한 상품문의의 간단한 정보 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @param memberNo 회원 번호
     * @return 상품문의의 간단한 정보 리스트 페이징 정보
     */
    PageResponse<GetInquirySummaryMemberResponseDto> requestInquiryMemberList(
            Pageable pageable, Long memberNo);

    /**
     * 불량상품 문의의 간단한 정보 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이징 정보
     * @return 불량 상품문의의 간단한 정보 리스트 페이징 정보
     */
    PageResponse<GetInquirySummaryResponseDto> requestErrorInquiryList(Pageable pageable);

    /**
     * 모든 문의(불량 문의 제외)의 간단한 정보 리스트를 조회하기 위한 메서드입니다.
     *
     * @param pageable       페이징 정보
     * @param searchKeyFir   검색 조건
     * @param searchValueFir 검색 값
     * @param searchKeySec   검색 조건 두번째
     * @param searchValueSec 검색 값 두번째
     * @return the page response
     */
    PageResponse<GetInquirySummaryResponseDto> requestInquiryList(
            Pageable pageable, String searchKeyFir,
            String searchValueFir, String searchKeySec, String searchValueSec);

    /**
     * 상품문의 단건 조회를 위한 메서드입니다.
     * 비공개 문의글일 경우 사용됩니다.
     *
     * @param inquiryNo 조회할 상품문의 번호
     * @param memberNo  회원 번호
     * @return 상품문의의 자세한 정보가 담긴 dto
     */
    GetInquiryResponseDto requestPrivateInquiry(Long inquiryNo, Long memberNo);

    /**
     * 상품문의 단건 조회를 위한 메서드입니다.
     * 공개 문의글일 경우 사용됩니다.
     *
     * @param inquiryNo 조회할 상품문의 번호
     * @return 상품문의의 자세한 정보가 담긴 dto
     */
    GetInquiryResponseDto requestPublicInquiry(Long inquiryNo);
}
