package com.bookpub.bookpubfront.order.adaptor;

import com.bookpub.bookpubfront.order.dto.CreateOrderRequestDto;
import com.bookpub.bookpubfront.order.dto.GetOrderDetailResponseDto;
import com.bookpub.bookpubfront.order.dto.GetOrderListResponseDto;
import com.bookpub.bookpubfront.state.OrderState;
import com.bookpub.bookpubfront.state.anno.StateCode;
import com.bookpub.bookpubfront.utils.PageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.validation.constraints.Min;

/**
 * 주문 api 와 연동하기 위한 adaptor 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface OrderAdaptor {

    /**
     * 주문을 생성하기 위한 메서드입니다.
     *
     * @param requestDto 생성을 위한 Dto.
     * @throws JsonProcessingException Json 변환 중 발생할 수 있는 예외.
     */
    void createOrderRequest(CreateOrderRequestDto requestDto) throws JsonProcessingException;

    /**
     * 관리자가 모든 주문을 조회하기 위한 메서드입니다.
     *
     * @param page 페이지의 번호입니다.
     * @return 페이지를 반환합니다.(사이즈는 10으로 고정됩니다.)
     */
    PageResponse<GetOrderListResponseDto> getAllOrdersRequest(@Min(0) Integer page);

    /**
     * 회원번호로 주문목록을 조회합니다.
     *
     * @param page 페이지 번호입니다.
     * @param memberNo 회원번호입니다.
     * @return 페이지를 반환합니다.
     */
    PageResponse<GetOrderListResponseDto> getAllOrdersByMemberNoRequest(
            @Min(0) Integer page, Long memberNo);

    /**
     * 주문을 상세조회하기 위한 메서드입니다.
     *
     * @param orderNo 주문번호입니다.
     * @return 주문상세 Dto 반환.
     */
    GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo);

    /**
     * 송장번호를 수정하기 위한 메서드입니다.
     *
     * @param orderNo 주문번호입니다.
     * @param invoiceNo 송장번호입니다.
     */
    void modifyInvoiceNoRequest(Long orderNo, String invoiceNo);

    /**
     * 주문상태코드를 수정하기 위한 메서드입니다.
     *
     * @param orderNo 주문번호입니다.
     * @param codeName 코드명입니다.
     */
    void modifyStateCodeRequest(Long orderNo,
                                @StateCode(enumClass = OrderState.class)
                                String codeName);
}
