package com.nhnacademy.bookpub.bookpubfront.payment.controller;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.config.TossConfig;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderConfirmResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.OrderProductRefundRequestDto;
import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.RefundRequestDto;
import com.nhnacademy.bookpub.bookpubfront.payment.service.PaymentService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 결제 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final TossConfig tossConfig;
    private final MemberUtils memberUtils;
    private final CategoryUtils categoryUtils;
    private final CartUtils cartUtils;

    private static final String WAITING_PAYMENT = "결제대기";

    /**
     * 결제페이지를 로딩하는 컨트롤러입니다.
     *
     * @param orderNo 주문번호.
     * @param model   model.
     * @return 결제페이지.
     */
    @GetMapping("/{orderNo}")
    public String paymentPage(@PathVariable Long orderNo, Model model,
                              @CookieValue(name = CartUtils.CART_COOKIE, required = false) Cookie cookie) {
        GetOrderConfirmResponseDto orderConfirmInfo =
                orderService.getOrderConfirmInfo(orderNo);

        if (!orderConfirmInfo.getOrderState().equals(WAITING_PAYMENT)) {
            return "error/401";
        }

        memberUtils.modelRequestMemberNo(model);
        categoryUtils.categoriesView(model);
        cartUtils.getCountInCart(cookie.getValue(), model);

        model.addAttribute("order", orderConfirmInfo);
        model.addAttribute("toss", tossConfig.makeTossProvider());
        return "payment/main";
    }

    /**
     * 결제 생성 성공 시 리다이렉트 되서 들어오는 컨트롤러 url.
     *
     * @param orderId    주문번호.
     * @param paymentKey 결제 생성 키.
     * @param amount     금액.
     * @return successPage.
     */
    @GetMapping("/success")
    public String successPage(HttpServletResponse response,
                              @RequestParam String orderId,
                              @RequestParam String paymentKey,
                              @RequestParam Long amount) {
        paymentService.verifyPayment(orderId, amount);
        paymentService.createPayment(orderId, paymentKey, amount, response);

        return "redirect:/?order=" + orderId;
    }

    /**
     * 결제를 취소하여 환불 받은 후 주문내역 페이지로 다시 돌아가는 컨트롤러.
     *
     * @param refundRequestDto 환불 정보.
     * @return 환불 후 주문내역으로.
     */
    @PostMapping("/refund")
    public String orderRefundRequest(@Valid RefundRequestDto refundRequestDto) {
        Long memberNo = memberUtils.getMemberNo();
        paymentService.refundOrder(refundRequestDto, memberNo);

        return "redirect:/orders/list";
    }

    /**
     * 주문상품의 결제를 취소하여 환불 받은 후 주문상세 페이지로 다시 돌아가는 컨트롤러.
     *
     * @param orderProductRefundRequestDto 환불 정보.
     * @return 환불.
     */
    @PostMapping("/order-product/refund")
    public String orderProductRefundRequest(
            @Valid OrderProductRefundRequestDto orderProductRefundRequestDto) {
        Long memberNo = memberUtils.getMemberNo();
        paymentService.refundOrderProduct(orderProductRefundRequestDto, memberNo);

        return "redirect:/orders?orderNo=" + orderProductRefundRequestDto.getOrderNo();
    }

    /**
     * 주문상품을 교환신청 하는 메소드.
     *
     * @param orderProductRefundRequestDto 환불 정보.
     * @return 환불.
     */
    @PostMapping("/order-product/exchange")
    public String orderProductExchangeRequest(
            @Valid OrderProductRefundRequestDto orderProductRefundRequestDto) {
        Long memberNo = memberUtils.getMemberNo();
        paymentService.exchangeOrderProduct(orderProductRefundRequestDto, memberNo);

        return "redirect:/orders?orderNo=" + orderProductRefundRequestDto.getOrderNo();
    }
}
