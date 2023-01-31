package com.nhnacademy.bookpub.bookpubfront.order.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.OrderProductDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 회원의 주문에 관련된 뷰를 위한 컨트롤러입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class MemberOrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    /**
     * 멤버의 주문 리스트 조회를 위한 메소드입니다.
     *
     * @param model    모델.
     * @param pageable 페이징.
     * @return 주문리스트 뷰를 반환합니다.
     */
    @GetMapping("/list")
    @Auth
    public String orderListView(Model model, @PageableDefault Pageable pageable) {
        Long memberNo = Long.parseLong(
                (String) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());

        model.addAttribute("member", memberService.getMember(memberNo));
        model.addAttribute("orderList", orderService.getOrderListByMemberNo(memberNo, pageable));
        model.addAttribute("nowPage", pageable.getPageNumber());

        return "mypage/orderList";
    }

    /**
     * 주문 상세 뷰를 위한 메소드입니다.
     *
     * @param model   모델.
     * @param orderNo 주문번호.
     * @return 주문상세 뷰를 반환합니다.
     */
    @GetMapping()
    public String orderDetailView(Model model, @RequestParam Long orderNo) {
        Long memberNo = Long.parseLong(
                (String) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());

        model.addAttribute("member", memberService.getMember(memberNo));
        model.addAttribute("orderDetail", orderService.getOrderDetailByNo(orderNo));

        return "mypage/orderDetail";
    }

    /**
     * 회원, 비회원의 주문 view로 넘겨주는 컨트롤러.
     *
     * @param model view에 데이터 전달해주는 클래스.
     * @return order view.
     */
    @GetMapping("/order")
    public String memberOrder(Model model,
                              @CookieValue(name = "orderInfo", required = false) String cart) {
        String principal =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            MemberDetailResponseDto member
                    = memberService.getMember(Long.parseLong(principal));
            model.addAttribute("member", member);
        }

        if (Objects.nonNull(cart)) {
            List<String> products = CartUtils.parsingCart(cart);
            List<OrderProductDto> orderProductDtoList = productService.orderProductInCart(products);

            model.addAttribute("products", orderProductDtoList);
        }

        return "order/main";
    }
}
