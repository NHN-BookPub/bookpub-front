package com.nhnacademy.bookpub.bookpubfront.order.controller;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.settingPagination;
import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListForAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 주문 관리자 페이지 뷰를 위한 컨트롤러.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    /**
     * 주문 상세 뷰를 위한 메소드입니다.
     *
     * @param model   모델.
     * @param orderNo 주문번호.
     * @return 주문상세 뷰를 반환합니다.
     */
    @GetMapping
    public String orderDetailView(Model model, @RequestParam Long orderNo) {
        Long memberNo = Long.parseLong(
                (String) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());

        GetOrderDetailResponseDto response = orderService.getOrderDetailByNo(orderNo, memberNo);

        if (response.getCouponAmount()
                + response.getPointAmount()
                + response.getTotalAmount() >= 30000) {
            response.setDeliveryAmountToZero();
        }

        if (!response.isPackaged()) {
            response.setPackageAmountToZero();
        }

        Long originPrice =
                response.getCouponAmount()
                        + response.getPointAmount()
                        + response.getTotalAmount()
                        - response.getDeliveryAmount()
                        - response.getPackageAmount();



        model.addAttribute("orderDetail", response);
        model.addAttribute("originPrice", originPrice);
        model.addAttribute("member", memberService.getApiMember(memberNo));

        return "admin/order/adminOrderDetail";
    }

    /**
     * 관리자용 주문 뷰.
     *
     * @param model 모델.
     * @param pageable 페이징.
     * @return 주문 리스트 뷰.
     */
    @Auth
    @GetMapping("/list")
    public String adminOrderView(Model model, @PageableDefault Pageable pageable) {
        PageResponse<GetOrderListForAdminResponseDto> orders =
                orderService.getOrderList(pageable);

        settingPagination(model, orders, "orderList");

        return "admin/order/orderMain";
    }
}
