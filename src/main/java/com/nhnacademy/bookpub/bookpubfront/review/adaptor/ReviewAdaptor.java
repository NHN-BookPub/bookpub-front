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
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface ReviewAdaptor {

    PageResponse<GetMemberReviewResponseDto> requestMemberReviews(Long memberNo, Pageable pageable);

    PageResponse<GetProductReviewResponseDto> requestProductReviews(Long productNo, Pageable pageable);

    PageResponse<GetProductSimpleResponseDto> requestMemberWritableReviews(Long memberNo, Pageable pageable);

    GetMemberReviewResponseDto requestReview(Long reviewNo);

    GetProductReviewInfoResponseDto requestProductReviewInfo(Long productNo);

    void requestCreateReview(CreateReviewRequestDto request, Long memberNo);

    void requestDeleteReview(Long reviewNo);

    void requestDeleteReviewImage(Long reviewNo);

    void requestModifyReview(Long reviewNo, ModifyReviewRequestDto request);
}
