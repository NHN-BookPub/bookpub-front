package com.nhnacademy.bookpub.bookpubfront.order.dto;

import com.nhnacademy.bookpub.bookpubfront.state.OrderState;
import com.nhnacademy.bookpub.bookpubfront.state.anno.StateCode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 주문등록을 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class CreateOrderRequestDto {
    @NotEmpty
    private List<Long> productNos;
    @NotEmpty
    private Map<Long, Integer> productAmounts;
    @NotEmpty
    private Map<Long, Long> productCouponAmounts;
    private Map<Long, String> orderProductReasons;
    @StateCode(enumClass = OrderState.class)
    private String orderState;
    @Length(max = 200)
    private String buyerName;
    @Length(max = 20)
    private String buyerNumber;
    @Length(max = 200)
    private String recipientName;
    @Length(max = 20)
    private String recipientNumber;
    @NotNull
    private String addressDetail;
    @NotNull
    private String roadAddress;
    @NotNull
    private LocalDateTime receivedAt;
    private boolean packaged;
    private String orderRequest;
    private Long pointAmount;
    private Long couponAmount;
    private Long totalAmount;
}
