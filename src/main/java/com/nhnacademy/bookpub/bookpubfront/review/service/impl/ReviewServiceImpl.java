package com.nhnacademy.bookpub.bookpubfront.review.service.impl;

import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductSimpleResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.adaptor.ReviewAdaptor;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.CreateReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.ModifyReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetMemberReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewInfoResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 상품평 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewAdaptor reviewAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetMemberReviewResponseDto> getMemberReviews(
            Long memberNo, Pageable pageable) {
        return reviewAdaptor.requestMemberReviews(memberNo, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductReviewResponseDto> getProductReviews(
            Long productNo, Pageable pageable) {
        return reviewAdaptor.requestProductReviews(productNo, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductSimpleResponseDto> getMemberWritableReviews(
            Long memberNo, Pageable pageable) {
        return reviewAdaptor.requestMemberWritableReviews(memberNo, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetMemberReviewResponseDto getReview(Long reviewNo) {
        return reviewAdaptor.requestReview(reviewNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetProductReviewInfoResponseDto getProductReviewInfo(Long productNo) {
        return reviewAdaptor.requestProductReviewInfo(productNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createReview(CreateReviewRequestDto request) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        reviewAdaptor.requestCreateReview(request, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReview(Long reviewNo) {
        reviewAdaptor.requestDeleteReview(reviewNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReviewImage(Long reviewNo) {
        reviewAdaptor.requestDeleteReviewImage(reviewNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyReview(Long reviewNo, ModifyReviewRequestDto request) {
        reviewAdaptor.requestModifyReview(reviewNo, request);
    }
}
