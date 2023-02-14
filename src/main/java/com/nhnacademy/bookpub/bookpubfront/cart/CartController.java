package com.nhnacademy.bookpub.bookpubfront.cart;

import static com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils.CART_COOKIE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 장바구니를 위한 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final ObjectMapper objectMapper;
    private final CartUtils cartUtils;
    private final CategoryUtils categoryUtils;
    private final MemberUtils memberUtils;
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 장바구니를 View 메서드.
     *
     * @param model view 요청을 보낼 request
     * @return 장바구니 화면
     */
    @GetMapping
    public String cartView(@CookieValue(name = CART_COOKIE, required = false) Cookie cookie,
                           Model model) {
        if (Objects.nonNull(cookie)) {
            Set<Object> objects = redisTemplate.opsForSet().members(cookie.getValue());

            List<Long> productNos = new ArrayList<>();

            if (Objects.nonNull(objects)) {
                for (Object object : objects) {
                    productNos.add(Long.valueOf(object.toString()));
                }
            }

            cartUtils.getCountInCart(cookie.getValue(), model);
            categoryUtils.categoriesView(model);
            memberUtils.modelRequestMemberNo(model);

            List<GetProductDetailResponseDto> products =
                    productService.findProductInCart(productNos);
            model.addAttribute("products", products);
        }

        return "cart/shoppingCart";
    }

    /**
     * 장바구니에 쿠키를 저장하는 메서드.
     *
     * @param productNo 상품 번호
     * @return 상품 번호
     */
    @PostMapping
    public @ResponseBody Object addProductToCart(@CookieValue(name = CART_COOKIE) Cookie cookie,
                                                 Long productNo) {
        redisTemplate.opsForSet().add(cookie.getValue(), productNo);

        return productNo;
    }

    @PostMapping("/{productNo}")
    public String deleteProductInCart(@PathVariable("productNo") Long productNo,
                                      @CookieValue(name = CART_COOKIE) String cartRedisKey) {
        cartUtils.deleteProductInCart(cartRedisKey, productNo);

        return "redirect:/cart";
    }

    /**
     * 장바구니에서 선택된 상품들만 결제 창으로 넘길 정보를 저장하는 메서드.
     *
     * @param cart     Json 형식의 상품과 구매할 갯수가 담긴 Data
     * @param response HttpServletResponse
     * @return 파싱된 Json Data
     * @throws JsonProcessingException Json 파싱중 생긴 Exception
     */
    @PostMapping("/order")
    public @ResponseBody Object saveOrderInfo(String cart, HttpServletResponse response)
            throws JsonProcessingException {
        List<Map<String, Object>> jsonData = objectMapper.readValue(cart, new TypeReference<>() {
        });
        StringBuilder sb = new StringBuilder();

        for (Map<String, Object> tmp : jsonData) {
            String productNo = String.valueOf(tmp.get("productNo"));
            String count = String.valueOf(tmp.get("count"));

            sb.append(productNo).append("-").append(count).append("/");
        }

        Cookie cookie = new Cookie(CartUtils.ORDER_INFO, sb.toString());
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        return jsonData;
    }

    /**
     * 바로 주문 정보를 만들어주는 메서드.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return 상품 번호
     */
    @PostMapping("/direct-order")
    public @ResponseBody Object directOrder(HttpServletRequest request, HttpServletResponse response) {
        String productNo = request.getParameter("productNo");

        Cookie cookie = new Cookie(CartUtils.ORDER_INFO, productNo + "-1");
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        return productNo;
    }

}