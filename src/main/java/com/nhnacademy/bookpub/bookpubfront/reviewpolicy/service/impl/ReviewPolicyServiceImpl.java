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
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class ReviewPolicyServiceImpl implements ReviewPolicyService {
    private final ReviewPolicyAdaptor reviewPolicyAdaptor;
    @Override
    public List<GetReviewPolicyResponseDto> getReviewPolicies() {
        return reviewPolicyAdaptor.requestReviewPolicies();
    }

    @Override
    public void createReviewPolicy(CreateReviewPolicyRequestDto request) {
        reviewPolicyAdaptor.requestCreateReviewPolicy(request);
    }

    @Override
    public void modifyReviewPolicy(ModifyReviewPolicyRequestDto request) {
        reviewPolicyAdaptor.requestModifyReviewPolicy(request);
    }

    @Override
    public void modifyReviewPolicyUsed(Integer policyNo) {
        reviewPolicyAdaptor.requestModifyUsed(policyNo);
    }
}
