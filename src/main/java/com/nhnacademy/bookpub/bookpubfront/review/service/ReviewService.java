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
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface ReviewService {

    PageResponse<GetMemberReviewResponseDto> getMemberReviews(Long memberNo, Pageable pageable);

    PageResponse<GetProductReviewResponseDto> getProductReviews(Long productNo, Pageable pageable);

    PageResponse<GetProductSimpleResponseDto> getMemberWritableReviews(Long memberNo, Pageable pageable);

    GetMemberReviewResponseDto getReview(Long reviewNo);

    GetProductReviewInfoResponseDto getProductReviewInfo(Long productNo);

    void createReview(CreateReviewRequestDto request);

    void deleteReview(Long reviewNo);

    void deleteReviewImage(Long reviewNo);

    void modifyReview(Long reviewNo, ModifyReviewRequestDto request);
}
