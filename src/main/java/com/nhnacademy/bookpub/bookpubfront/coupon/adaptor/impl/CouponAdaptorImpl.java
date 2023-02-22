package com.nhnacademy.bookpub.bookpubfront.coupon.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.coupon.adaptor.CouponAdaptor;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 쿠폰을 관리하기 위한 어댑터입니다.
 *
 * @author : 정유진, 김서현
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CouponAdaptorImpl implements CouponAdaptor {

    private final RestTemplate restTemplate;
    private static final String COUPON_AUTH_URL = "/token/coupons";
    private final MemberUtils memberUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestAddCoupon(CreateCouponRequestDto createRequestDto) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL;

        HttpEntity<CreateCouponRequestDto> entity =
                new HttpEntity<>(createRequestDto, makeHeader());
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> requestCoupons(Pageable pageable, String searchKey,
                                                             String search) {
        String url =
                UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .queryParam("searchKey", searchKey)
                        .queryParam("search", search)
                        .encode()
                        .toUriString();

        ResponseEntity<PageResponse<GetCouponResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> requestPositiveCoupons(Pageable pageable,
                                                                     Long memberNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/members/" + memberNo
                                + "/positive")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetCouponResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> requestNegativeCoupons(Pageable pageable,
                                                                     Long memberNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/members/" + memberNo
                                + "/negative")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetCouponResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyUsedPointCoupon(Long couponNo) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/" + couponNo
                + "/point/members/" + memberUtils.getMemberNo();

        restTemplate.exchange(url,
                HttpMethod.PUT,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean requestExistCouponsByMemberNo(Long memberNo, List<Long> tierCoupons) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/"
                                + memberNo + "/tier-coupons")
                .queryParam("tierCoupons", tierCoupons)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<Boolean>() {
                }).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestIssueTierCoupons(Long memberNo, List<Long> tierCoupons) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/"
                                + memberNo + "/tier-coupons")
                .queryParam("tierCoupons", tierCoupons)
                .encode().toUriString();

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void requestIssueMonthCoupon(Long memberNo, Long templateNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/"
                                + memberNo + "/month-coupon")
                .queryParam("templateNo", templateNo)
                .encode().toUriString();

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(makeHeader()),
                Void.class
        );

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean checkCouponMonthIssued(Long memberNo, Long templateNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/"
                                + memberNo + "/month-coupon")
                .queryParam("templateNo", templateNo)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<Boolean>() {
                }).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> requestCouponMonthDuplicate(Long memberNo, List<Long> templateList) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + COUPON_AUTH_URL + "/"
                                + memberNo + "/month-coupons/issue-check")
                .queryParam("memberNo", memberNo)
                .queryParam("couponList", templateList)
                .encode()
                .toUriString();

        ResponseEntity<List<Boolean>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
