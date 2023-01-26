package com.bookpub.bookpubfront.order.dto.request;

import com.bookpub.bookpubfront.state.OrderState;
import com.bookpub.bookpubfront.state.anno.StateCode;
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
    @NotEmpty
    private Map<Long, String> orderProductReasons;
    @StateCode(enumClass = OrderState.class)
    private String orderState;
    @Length(max = 200)
    private String buyerName; //dz
    @Length(max = 20)
    private String buyerNumber; //dz
    @Length(max = 200)
    private String recipientName; //dz
    @Length(max = 20)
    private String recipientNumber; //dz
    @NotNull
    private String addressDetail; //dz
    @NotNull
    private String roadAddress; //dz
    @NotNull
    private String receivedAt; //dz
    private boolean packaged; //dz
    private String orderRequest; //dz
    private Long pointAmount; //dz
    private Long couponAmount; //dz
    private Long totalAmount; //dz
}
