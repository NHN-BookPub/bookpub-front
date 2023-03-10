package com.nhnacademy.bookpub.bookpubfront.order.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.order.dto.request.GetOrderDetailNonMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.request.OrderFormRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.OrderProductDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetOrderPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.service.PricePolicyService;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ????????? ????????? ????????? ?????? ?????? ?????????????????????.
 *
 * @author : ?????????
 * @since : 1.0
 **/
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class MemberOrderController {
    private static final String MEMBER = "member";
    private static final Integer PACKING = 0;
    private static final Integer SHIPPING = 1;
    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;
    private final PricePolicyService pricePolicyService;
    private final MemberUtils memberUtils;
    private final CategoryUtils categoryUtils;
    private final CartUtils cartUtils;


    /**
     * ????????? ?????? ????????? ????????? ?????? ??????????????????.
     *
     * @param model    ??????.
     * @param pageable ?????????.
     * @return ??????????????? ?????? ???????????????.
     */
    @GetMapping("/list")
    @Auth
    public String orderListView(Model model, @PageableDefault Pageable pageable) {
        Long memberNo = Long.parseLong(
                (String) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());

        PageResponse<GetOrderListResponseDto> orders =
                orderService.getOrderListByMemberNo(memberNo, pageable);

        model.addAttribute(MEMBER, memberService.getTokenMember(memberNo));
        Utils.settingPagination(model, orders, "orderList");

        return "mypage/orderList";
    }

    /**
     * ?????? ?????? ?????? ?????? ??????????????????.
     *
     * @param model   ??????.
     * @param orderNo ????????????.
     * @return ???????????? ?????? ???????????????.
     */
    @GetMapping
    public String orderDetailView(Model model, @RequestParam Long orderNo) {
        MemberDetailResponseDto member = memberUtils.getMember();
        GetOrderDetailResponseDto response = orderService.getOrderDetailByNo(orderNo, member.getMemberNo());

        if (!member.getMemberNo().equals(response.getMemberNo())) {
            model.addAttribute("orderDetail", null);
        } else {
            setResponseInModel(model, response);
        }

        return "mypage/orderDetail";
    }

    /**
     * ???????????? ????????? ????????? ????????????.
     *
     * @param model ??????
     * @param response ???????????? Dto
     */
    private void setResponseInModel(Model model, GetOrderDetailResponseDto response) {
        Long originPrice = setOriginPrice(response);

        originPrice = addDeliveryPackageFee(response, originPrice);

        model.addAttribute("orderDetail", response);
        model.addAttribute("originPrice", originPrice);
    }

    /**
     * ?????? ????????? ???????????? ???????????? ???????????????.
     *
     * @param response ???????????? dto
     * @param originPrice ????????????
     * @return ?????? ??????
     */
    private Long addDeliveryPackageFee(GetOrderDetailResponseDto response, Long originPrice) {
        if (originPrice >= 30000) {
            response.setDeliveryAmountToZero();
        }

        if (!response.isPackaged()) {
            response.setPackageAmountToZero();
        }

        originPrice -= (response.getDeliveryAmount() + response.getPackageAmount());
        return originPrice;
    }

    /**
     * ??????????????? ???????????? ?????? ????????? ???????????????.
     *
     * @param response ???????????? dto
     * @return ?????? ??????
     */
    private Long setOriginPrice(GetOrderDetailResponseDto response) {
        return response.getCouponAmount()
                + response.getPointAmount()
                + response.getTotalAmount();
    }

    /**
     * ????????? ?????? ????????????????????????.
     *
     * @param model   ??????
     * @param request dto
     * @return ?????? ?????? ???
     */
    @PostMapping("/non")
    public String orderDetailViewNonMember(Model model,
                                           GetOrderDetailNonMemberRequestDto request) {

        GetOrderDetailResponseDto response =
                orderService.getOrderDetailResponseDto(request.getOrderId(), request.getPhoneNo());

        setResponseInModel(model, response);

        return "order/NonMemberOrderDetail";
    }

    /**
     * ??????, ???????????? ?????? view??? ???????????? ????????????.
     *
     * @param model view??? ????????? ??????????????? ?????????.
     * @return order view.
     */
    @GetMapping("/order")
    public String memberOrder(Model model,
                              @CookieValue(name = CartUtils.CART_COOKIE, required = false) String cartCookie,
                              @CookieValue(name = "orderInfo", required = false) String cart) {
        boolean isLoginUser = false;
        String principal =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            MemberDetailResponseDto member
                    = memberService.getApiMember(Long.parseLong(principal));
            isLoginUser = true;
            model.addAttribute(MEMBER, member);
        }

        if (Objects.nonNull(cart)) {
            List<String> products = CartUtils.parsingCart(cart);
            List<OrderProductDto> orderProductDtoList =
                    productService.orderProductInCart(products, isLoginUser);

            model.addAttribute("products", orderProductDtoList);
        }

        List<GetOrderPolicyResponseDto> orderRequestPolicy
                = pricePolicyService.getOrderRequestPolicy();

        categoryUtils.categoriesView(model);
        cartUtils.getCountInCart(cartCookie, model);
        model.addAttribute("packPolicy", orderRequestPolicy.get(PACKING));
        model.addAttribute("shipPolicy", orderRequestPolicy.get(SHIPPING));

        return "order/main";
    }

    /**
     * order from?????? ????????? ?????? shop????????? ????????? ??? ?????????????????? ?????????????????? controller.
     *
     * @param productInfo ???????????? ??????.
     * @param requestDto  ?????? ??????.
     * @return ???????????????.
     */
    @PostMapping("/order")
    public String orderComplete(@RequestParam(value = "productCoupon") String productInfo,
                                @ModelAttribute OrderFormRequestDto requestDto) {
        Long orderNo = orderService.createOrder(requestDto, productInfo);

        return "redirect:/payment/" + orderNo;
    }

    @GetMapping("/ebooks")
    public String viewEbooksByMember(Model model, @PageableDefault Pageable pageable) {
        Long memberNo = memberUtils.getMemberNo();
        memberUtils.modelRequestMemberNo(model);

        PageResponse<GetProductByCategoryResponseDto> products =
                orderService.getEbooksByMember(pageable, memberNo);

        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("isNext", products.isNext());
        model.addAttribute("isPrevious", products.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPageEbooks";
    }

    /**
     * ?????? ????????? ????????? ?????????????????? ????????? ????????? ?????????.
     *
     * @param orderProductNo ??????????????????.
     * @return ??????????????? ?????????.
     */
    @GetMapping("/confirm/order-product/{orderProductNo}")
    public String orderBuyConfirm(@PathVariable String orderProductNo) {
        Long memberNo = memberUtils.getMemberNo();
        orderService.confirmOrderProduct(orderProductNo, memberNo);

        return "redirect:/orders/list";
    }

    @GetMapping("/confirm/order/{orderNo}")
    public String orderProductBuyConfirm(@PathVariable String orderNo) {
        return "redirect:/orders/list";
    }
}
