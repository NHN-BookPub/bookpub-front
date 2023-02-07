package com.nhnacademy.bookpub.bookpubfront.review.adaptor;

import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductSimpleResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.CreateReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.ModifyReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetMemberReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewInfoResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * shop 서버와 통신하기 위한 상품평 어댑터입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface ReviewAdaptor {

    /**
     * 해당 회원이 작성한 상품평들을 조회하기 위한 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @param pageable 페이지 정보
     * @return 회원이 작성한 상품평 정보를 담은 Dto 페이지정보
     */
    PageResponse<GetMemberReviewResponseDto> requestMemberReviews(Long memberNo, Pageable pageable);

    /**
     * 해당 상품에 작성된 상품평들을 조회하기 위한 메서드입니다.
     *
     * @param productNo 상품 번호
     * @param pageable  페이지 정보
     * @return 상품에 작성된 상품평 정보를 담은 Dto 페이지 정보
     */
    PageResponse<GetProductReviewResponseDto> requestProductReviews(
            Long productNo, Pageable pageable);

    /**
     * 해당 회원이 상품평 작성 가능한 상품들의 정보를 조회하기 위한 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @param pageable 페이지 정보
     * @return 상품의 간단한 정보를 담은 Dto 페이지 정보
     */
    PageResponse<GetProductSimpleResponseDto> requestMemberWritableReviews(
            Long memberNo, Pageable pageable);

    /**
     * 상품평 단건 조회를 위한 메서드입니다.
     *
     * @param reviewNo 조회할 상품평 번호
     * @return 상품평 정보를 담은 Dto
     */
    GetMemberReviewResponseDto requestReview(Long reviewNo);

    /**
     * 해당 상품에 대한 간단한 상품평 정보를 조회하기 위한 메서드입니다.
     *
     * @param productNo 조회할 상품 번호
     * @return 간단한 상품평 정보를 담은 Dto
     */
    GetProductReviewInfoResponseDto requestProductReviewInfo(Long productNo);

    /**
     * 상품평 등록을 위한 메서드입니다.
     *
     * @param request  상품평 등록에 필요한 정보를 담은 Dto
     * @param memberNo 회원 번호
     */
    void requestCreateReview(CreateReviewRequestDto request, Long memberNo);

    /**
     * 상품평 삭제를 위한 메서드입니다.
     *
     * @param reviewNo 삭제할 상품평 번호
     */
    void requestDeleteReview(Long reviewNo);

    /**
     * 상품평의 이미지 삭제를 위한 메서드입니다.
     *
     * @param reviewNo 이미지를 삭제할 상품평 번호
     */
    void requestDeleteReviewImage(Long reviewNo);

    /**
     * 상품평 수정을 위한 메서드입니다.
     *
     * @param reviewNo 수정할 상품평 번호
     * @param request  수정할 상품평 정보를 담은 Dto
     */
    void requestModifyReview(Long reviewNo, ModifyReviewRequestDto request);
}
