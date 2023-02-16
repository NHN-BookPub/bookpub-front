package com.nhnacademy.bookpub.bookpubfront.order.dto.request;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 주문등록을 위한 Dto.
 *
 * @author : 여운석, 임태원
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
@ToString
public class CreateOrderRequestDto {
    private List<Long> productNos;
    private Map<Long, Integer> productCount;
    private Map<Long, Long> productAmount;
    private Map<Long, Long> productCoupon;
    private Map<Long, Long> productSaleAmount;
    private Map<Long, Long> productPointSave;
    private Long memberNo;
    private Integer deliveryFeePolicyNo;
    private Integer packingFeePolicyNo;
    private String buyerName;
    private String buyerNumber;
    private String recipientName;
    private String recipientNumber;
    private String addressDetail;
    private String roadAddress;
    private LocalDateTime receivedAt;
    private boolean packaged;
    private String orderRequest;
    private Long pointAmount;
    private Long couponAmount;
    private Long totalAmount;
    private Long savePoint;
    private String orderName;

    /**
     * order form에서 받은 dto객체로 shop과 통신할 requestDto로 만듭니다.
     *
     * @param orderInfo         주문정보.
     * @param productNos        주문상품 번호 리스트.
     * @param productCount      주문상품별 상품 개수 맵.
     * @param productAmount     주문상품 별 쿠폰 할인금액 맵.
     * @param productCoupon     상품에 적용된 쿠폰 맵.
     * @param productSaleAmount 상품에 적용된 쿠폰 할인가 맵.
     * @param productPointSave  상품 구매시 적립되는 포인트 액수.
     */
    @Builder
    public CreateOrderRequestDto(OrderFormRequestDto orderInfo,
                                 Long memberNo,
                                 List<Long> productNos,
                                 Map<Long, Integer> productCount,
                                 Map<Long, Long> productAmount,
                                 Map<Long, Long> productCoupon,
                                 Map<Long, Long> productSaleAmount,
                                 Map<Long, Long> productPointSave) {
        final String[] receive = orderInfo.getReceivedAt().split("-");
        packaged = orderInfo.getPackaged() != null;

        this.productNos = productNos;
        this.productCount = productCount;
        this.productAmount = productAmount;
        this.productCoupon = productCoupon;
        this.productSaleAmount = productSaleAmount;
        this.productPointSave = productPointSave;
        this.memberNo = memberNo;
        this.deliveryFeePolicyNo = orderInfo.getDeliveryFeePolicyNo();
        this.packingFeePolicyNo = orderInfo.getPackingFeeFeePolicyNo();
        this.buyerName = orderInfo.getBuyerName();
        this.buyerNumber = orderInfo.getBuyerNumber();
        this.recipientName = orderInfo.getRecipientName();
        this.recipientNumber = orderInfo.getRecipientNumber();
        this.addressDetail = orderInfo.getAddressDetail();
        this.roadAddress = orderInfo.getRoadAddress();
        this.receivedAt = LocalDateTime.of(
                Integer.parseInt(receive[0]),
                Integer.parseInt(receive[1]),
                Integer.parseInt(receive[2]), 12, 0);
        this.orderRequest = orderInfo.getOrderRequest();
        this.pointAmount = orderInfo.getPointAmount();
        this.couponAmount = orderInfo.getCouponAmount();
        this.totalAmount = orderInfo.getTotalAmount();
        this.savePoint = orderInfo.getSavePoint();
        this.orderName = orderInfo.getOrderName();
    }
}
