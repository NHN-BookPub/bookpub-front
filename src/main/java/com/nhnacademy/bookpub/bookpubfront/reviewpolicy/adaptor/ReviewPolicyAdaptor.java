package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.adaptor;

import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.CreateReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.ModifyReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.response.GetReviewPolicyResponseDto;
import java.util.List;

/**
 * 상품평 정책 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface ReviewPolicyAdaptor {
    /**
     * 상품평 정책 리스트 조회를 위한 메서드입니다.
     *
     * @return 상품평 정책 정보를 담은 Dto 리스트
     */
    List<GetReviewPolicyResponseDto> requestReviewPolicies();

    /**
     * 상품평 정책 등록을 위한 메서드입니다.
     *
     * @param request 상품평 정책 등록에 필요한 정보를 담은 Dto
     */
    void requestCreateReviewPolicy(CreateReviewPolicyRequestDto request);

    /**
     * 상품평 정책 수정을 위한 메서드입니다.
     *
     * @param request 상품평 정책 수정에 필요한 정보를 담은 Dto
     */
    void requestModifyReviewPolicy(ModifyReviewPolicyRequestDto request);

    /**
     * 상품평 정책 현재 사용여부 수정을 위한 메서드입니다.
     *
     * @param policyNo 수정할 상품평 정책 번호
     */
    void requestModifyUsed(Integer policyNo);
}
