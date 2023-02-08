package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.service;

import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.CreateReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.ModifyReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.response.GetReviewPolicyResponseDto;
import java.util.List;

/**
 * 상품평 정책 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface ReviewPolicyService {
    /**
     * 상품평 정책 리스트 조회를 위한 메서드입니다.
     *
     * @return 상품평정책 정보가 담긴 Dto 리스트
     */
    List<GetReviewPolicyResponseDto> getReviewPolicies();

    /**
     * 상품평 정책 사용 여부 수정을 위한 메서드입니다.
     *
     * @param policyNo 수정할 상품평 정책 번호
     */
    void modifyReviewPolicyUsed(Integer policyNo);

    /**
     * 상품평 정책 등록을 위한 메서드입니다.
     *
     * @param request 상품평 정책 등록에 필요한 정보를 담은 Dto
     */
    void createReviewPolicy(CreateReviewPolicyRequestDto request);

    /**
     * 상품평 정책을 수정하기 위한 메서드입니다.
     *
     * @param request 수정할 상품평 정책 번호
     */
    void modifyReviewPolicy(ModifyReviewPolicyRequestDto request);
}
