package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.adaptor;

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
public interface ReviewPolicyAdaptor {
    List<GetReviewPolicyResponseDto> requestReviewPolicies();

    void requestCreateReviewPolicy(CreateReviewPolicyRequestDto request);

    void requestModifyReviewPolicy(ModifyReviewPolicyRequestDto request);
    void requestModifyUsed(Integer policyNo);
}
