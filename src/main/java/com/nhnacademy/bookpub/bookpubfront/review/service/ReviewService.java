package com.nhnacademy.bookpub.bookpubfront.review.service;

import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductSimpleResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.CreateReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.ModifyReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetMemberReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewInfoResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 상품평 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface ReviewService {

    /**
     * 해당 회원이 작성한 리뷰들을 조회하기 위한 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @param pageable 페이지정보
     * @return 회원 리뷰 정보들이 담긴 Dto 페이지정보
     */
    PageResponse<GetMemberReviewResponseDto> getMemberReviews(Long memberNo, Pageable pageable);

    /**
     * 해당 상품의 리뷰들을 조회하기 위한 메서드입니다.
     *
     * @param productNo 상품 번호
     * @param pageable  페이지 정보
     * @return 상품의 리뷰 정보들이 담긴 Dto 페이지정보
     */
    PageResponse<GetProductReviewResponseDto> getProductReviews(Long productNo, Pageable pageable);

    /**
     * 해당 회원이 상품평 작성 가능한 상품들을 조회하기 위한 메서드입니다.
     *
     * @param memberNo 회원 번호
     * @param pageable 페이지정보
     * @return 상품평 작성 가능한 상품 정보들이 담긴 Dto 페이지정보
     */
    PageResponse<GetProductSimpleResponseDto> getMemberWritableReviews(
            Long memberNo, Pageable pageable);

    /**
     * 상품평 단건 조회를 위한 메서드입니다.
     * 상품평 수정 시 사용됩니다.
     *
     * @param reviewNo 상품평 번호
     * @return 상품평 정보가 담긴 Dto
     */
    GetMemberReviewResponseDto getReview(Long reviewNo);

    /**
     * 상품의 상품평 요약 정보를 조회하기 위한 메서드입니다.
     * 상품의 총평점, 평점개수 등의 정보를 가져올 수 있습니다.
     *
     * @param productNo 상품 번호
     * @return 상품의 상품평 요약정보가 담긴 Dto
     */
    GetProductReviewInfoResponseDto getProductReviewInfo(Long productNo);

    /**
     * 상품평을 등록하기 위한 메서드입니다.
     *
     * @param request 상품평 등록에 필요한 정보를 담은 Dto
     */
    void createReview(CreateReviewRequestDto request);

    /**
     * 상품평 삭제를 위한 메서드입니다.
     *
     * @param reviewNo 삭제할 상품평 번호
     */
    void deleteReview(Long reviewNo);

    /**
     * 해당 상품평의 이미지 삭제를 위한 메서드입니다.
     * 상품평 수정 시 사용됩니다.
     *
     * @param reviewNo 이미지를 삭제할 상품평 번호
     */
    void deleteReviewImage(Long reviewNo);

    /**
     * 해당 상품평을 수정하기 위한 메서드입니다.
     * 상품평의 별점, 상품평 내용, 이미지 정보가 수정됩니다.
     *
     * @param reviewNo 수정할 상품평 번호
     * @param request  상품평을 수정하기 위한 정보를 담은 Dto
     */
    void modifyReview(Long reviewNo, ModifyReviewRequestDto request);
}
