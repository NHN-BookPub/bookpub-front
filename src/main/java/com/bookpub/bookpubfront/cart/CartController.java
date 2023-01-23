package com.bookpub.bookpubfront.cart;

import com.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.bookpub.bookpubfront.product.service.ProductService;
import com.bookpub.bookpubfront.utils.CartUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 장바구니를 위한 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private static final String PRODUCT = "product";
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    /**
     * 장바구니를 View 메서드.
     *
     * @param request HttpServletRequest
     * @param model   view 요청을 보낼 request
     * @return 장바구니 화면
     */
    @GetMapping
    public String cartView(HttpServletRequest request, Model model) {
        CartUtils.getCountInCart(request, model);

        Cookie[] cookies = request.getCookies();
        List<Long> productsNo = new ArrayList<>();

        for (Cookie cookie : cookies) {
            if (cookie.getName().contains(PRODUCT)) {
                productsNo.add(Long.parseLong(cookie.getValue()));
            }
        }

        List<GetProductDetailResponseDto> products = productService.findProductInCart(productsNo);
        model.addAttribute("products", products);

        return "cart/shoppingCart";
    }

    /**
     * 장바구니에 쿠키를 저장하는 메서드.
     *
     * @param productNo 상품 번호
     * @param response  HttpServletResponse
     * @return 상품 번호
     */
    @PostMapping("/add")
    public @ResponseBody Object addProductToCart(Long productNo,
                                                 HttpServletResponse response) {
        String uuid = UUID.randomUUID().toString();

        Cookie cookie = new Cookie(uuid + "-" + PRODUCT, String.valueOf(productNo));
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        return productNo;
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
    public @ResponseBody Object testOrder(String cart, HttpServletResponse response)
            throws JsonProcessingException {
        List<Map<String, Object>> jsonData = objectMapper.readValue(cart, new TypeReference<>() {
        });
        String orderInfo = "";

        for (Map<String, Object> tmp : jsonData) {
            String productNo = String.valueOf(tmp.get("productNo"));
            String count = String.valueOf(tmp.get("count"));

            orderInfo += productNo + "-" + count + "/";
        }

        Cookie cookie = new Cookie("orderInfo", orderInfo);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        return jsonData;
    }

    @GetMapping("/order")
    public String order(@CookieValue(value = "orderInfo", required = false) String orderInfo, Model model) {
        model.addAttribute("info", orderInfo);

        return "cart/tmp";
    }
}