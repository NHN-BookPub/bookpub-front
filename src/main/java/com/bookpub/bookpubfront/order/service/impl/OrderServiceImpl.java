package com.bookpub.bookpubfront.order.service.impl;

import com.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.bookpub.bookpubfront.order.dto.CreateOrderRequestDto;
import com.bookpub.bookpubfront.order.dto.GetOrderDetailResponseDto;
import com.bookpub.bookpubfront.order.dto.GetOrderListResponseDto;
import com.bookpub.bookpubfront.order.service.OrderService;
import com.bookpub.bookpubfront.utils.PageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 주문 서비스의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdaptor orderAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrder(CreateOrderRequestDto requestDto)
            throws JsonProcessingException {
        orderAdaptor.createOrderRequest(requestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyInvoiceNo(Long orderNo, String invoiceNo) {
        orderAdaptor.modifyInvoiceNoRequest(orderNo, invoiceNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyStateCode(Long orderNo, String codeName) {
        orderAdaptor.modifyStateCodeRequest(orderNo, codeName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderDetailResponseDto getOrderDetailByNo(Long orderNo) {
        return orderAdaptor.getOrderDetailByOrderNoRequest(orderNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListResponseDto> getOrderList(Integer page) {
        return orderAdaptor.getAllOrdersRequest(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListResponseDto> getOrderListByMemberNo(Long memberNo, Integer page) {
        return orderAdaptor.getAllOrdersByMemberNoRequest(page, memberNo);
    }
}
