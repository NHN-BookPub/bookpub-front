package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.service;

import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.CreateReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.ModifyReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.response.GetReviewPolicyResponseDto;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface ReviewPolicyService {
    List<GetReviewPolicyResponseDto> getReviewPolicies();

    void modifyReviewPolicyUsed(Integer policyNo);

    void createReviewPolicy(CreateReviewPolicyRequestDto request);

    void modifyReviewPolicy(ModifyReviewPolicyRequestDto request);
}
