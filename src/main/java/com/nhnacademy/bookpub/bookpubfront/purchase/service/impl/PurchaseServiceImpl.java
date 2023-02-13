package com.nhnacademy.bookpub.bookpubfront.purchase.service.impl;

import com.nhn.dooray.client.DoorayHook;
import com.nhnacademy.bookpub.bookpubfront.config.DoorayConfig;
import com.nhnacademy.bookpub.bookpubfront.purchase.adaptor.PurchaseAdaptor;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.request.CreatePurchaseRequestDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.response.GetPurchaseListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.service.PurchaseService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetAppliedMemberResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 구매이력 서비스의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final DoorayConfig doorayConfig;
    private final PurchaseAdaptor purchaseAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPurchaseListResponseDto> getPurchases(Pageable pageable) {
        return purchaseAdaptor.getPurchases(pageable);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public void createPurchaseAndAlarm(CreatePurchaseRequestDto request) {
        List<GetAppliedMemberResponseDto> members = purchaseAdaptor.createPurchase(request);

        for (GetAppliedMemberResponseDto next : members) {
            String stringBuilder = "To. " +
                    next.getMemberNickname() +
                    "님 '" +
                    next.getTitle() +
                    "' 책이 입고 되었습니다.\n";

            doorayConfig.doorayHookWishlistAlarmSender()
                    .send(DoorayHook.builder()
                            .botName("wishlist")
                            .text(stringBuilder)
                            .build());
        }
//
//        if (!members.iterator().hasNext()) {
//            StringBuilder sb = makingMessage(members);
//            doorayConfig.doorayHookWishlistAlarmSender().send(
//                    DoorayHook.builder()
//                            .botName("wishlist")
//                            .text(sb.toString())
//                            .build()
//            );
//        }
    }

    /**
     * 회원들에게 알림 메세지를 만드는 메서드 입니다.
     *
     * @param members 위시리스트에 알림을 설정한 멤버들.
     * @return StringBuilder
     */
    private StringBuilder makingMessage(List<GetAppliedMemberResponseDto> members) {
        StringBuilder sb = new StringBuilder();
        for (GetAppliedMemberResponseDto member : members) {
            sb.append("To. ");
            sb.append(member.getMemberNickname());
            sb.append("님 '");
            sb.append(member.getTitle());
            sb.append("' 책이 입고 되었습니다.\n");
        }
        return sb;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPurchaseListResponseDto> getPurchasesByProductNo(
            Long productNo, Pageable pageable) {
        return purchaseAdaptor.getPurchasesByProductNo(productNo, pageable);
    }
}
