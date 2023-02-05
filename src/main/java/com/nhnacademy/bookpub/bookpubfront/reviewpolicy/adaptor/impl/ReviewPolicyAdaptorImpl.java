package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.adaptor.ReviewPolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.CreateReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.ModifyReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.response.GetReviewPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class ReviewPolicyAdaptorImpl implements ReviewPolicyAdaptor {
    private final RestTemplate restTemplate;
    private static final String REVIEW_POLICY_URL = "/api/review-policies";

    @Override
    public List<GetReviewPolicyResponseDto> requestReviewPolicies() {
        String url = GateWayConfig.getGatewayUrl() + REVIEW_POLICY_URL;

        ResponseEntity<List<GetReviewPolicyResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                }
        );

        checkError(response);

        return response.getBody();
    }

    @Override
    public void requestCreateReviewPolicy(CreateReviewPolicyRequestDto request) {
        String url = GateWayConfig.getGatewayUrl() + REVIEW_POLICY_URL;

        HttpEntity<CreateReviewPolicyRequestDto> entity = new HttpEntity<>(request, Utils.makeHeader());

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );

        checkError(response);
    }

    @Override
    public void requestModifyReviewPolicy(ModifyReviewPolicyRequestDto request) {
        String url = GateWayConfig.getGatewayUrl() + REVIEW_POLICY_URL;

        HttpEntity<ModifyReviewPolicyRequestDto> entity = new HttpEntity<>(request, Utils.makeHeader());

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                Void.class
        );

        checkError(response);
    }

    @Override
    public void requestModifyUsed(Integer policyNo) {
        String url = GateWayConfig.getGatewayUrl() + REVIEW_POLICY_URL + "/" + policyNo + "/used";

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );

        checkError(response);
    }

    /**
     * 에러 체크를 위한 메소드입니다.
     *
     * @param response
     * @param <T>
     */
    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            throw new RuntimeException();
        }
    }
}