package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.service.impl;

import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.adaptor.ReviewPolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.CreateReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.ModifyReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.response.GetReviewPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.service.ReviewPolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 상품평 정책 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class ReviewPolicyServiceImpl implements ReviewPolicyService {
    private final ReviewPolicyAdaptor reviewPolicyAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetReviewPolicyResponseDto> getReviewPolicies() {
        return reviewPolicyAdaptor.requestReviewPolicies();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createReviewPolicy(CreateReviewPolicyRequestDto request) {
        reviewPolicyAdaptor.requestCreateReviewPolicy(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyReviewPolicy(ModifyReviewPolicyRequestDto request) {
        reviewPolicyAdaptor.requestModifyReviewPolicy(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyReviewPolicyUsed(Integer policyNo) {
        reviewPolicyAdaptor.requestModifyUsed(policyNo);
    }
}
