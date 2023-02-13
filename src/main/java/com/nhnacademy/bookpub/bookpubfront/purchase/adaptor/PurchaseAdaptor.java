package com.nhnacademy.bookpub.bookpubfront.purchase.adaptor;

import com.nhnacademy.bookpub.bookpubfront.purchase.dto.request.CreatePurchaseRequestDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.response.GetPurchaseListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetAppliedMemberResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * 구매이력 아답터입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface PurchaseAdaptor {
    /**
     * 최신순으로 매입이력을 불러옵니다.
     *
     * @param pageable 페이징.
     * @return 매입이력 리스트.
     */
    PageResponse<GetPurchaseListResponseDto> getPurchases(Pageable pageable);

    /**
     * 매입 이력을 등록합니다.
     *
     * @param request 이력등록에 필요한 정보.
     * @return 매입과 동시에 위시리스트에 알림여부를 등록한 멤버들 리스트
     */
    List<GetAppliedMemberResponseDto> createPurchase(CreatePurchaseRequestDto request);

    /**
     * 상품 번호로 이력을 조회합니다.
     *
     * @param productNo 상품번호
     * @param pageable 페이징
     * @return 매입이력 리스트
     */
    PageResponse<GetPurchaseListResponseDto> getPurchasesByProductNo(Long productNo, Pageable pageable);
}
