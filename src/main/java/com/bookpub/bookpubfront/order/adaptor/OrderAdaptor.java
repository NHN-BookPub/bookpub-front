package com.bookpub.bookpubfront.order.adaptor;

import com.bookpub.bookpubfront.order.dto.request.CreateOrderRequestDto;
import com.bookpub.bookpubfront.order.dto.response.GetAddressResponseDto;
import com.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.bookpub.bookpubfront.order.dto.response.GetOrderListResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import javax.validation.constraints.Min;

/**
 * 주문 api 와 연동하기 위한 adaptor 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface OrderAdaptor {

    void createOrderRequest(CreateOrderRequestDto requestDto) throws JsonProcessingException;

    PageResponse<GetOrderListResponseDto> getAllOrdersRequest(@Min(0) Integer page);

    GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo);

    List<GetAddressResponseDto> getMemberAddresses();
}
