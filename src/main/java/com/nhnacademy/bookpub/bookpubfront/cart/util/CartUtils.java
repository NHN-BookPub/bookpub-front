package com.nhnacademy.bookpub.bookpubfront.cart.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * 장바구니를 쿠키로 다루기 위한 Util class.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CartUtils {

    private final RedisTemplate<String, Object> redisTemplate;
    public static final String CART_COOKIE = "CART";
    public static final String ORDER_INFO = "orderInfo";

    /**
     * 현재 장바구니에 담긴 상품 개수를 구하는 메서드.
     *
     * @param redisKey Redis Key 값
     * @param model    model
     */
    public void getCountInCart(String redisKey, Model model) {
        Long count;
        if (Objects.isNull(redisTemplate.opsForSet().size(redisKey))) {
            count = 0L;
        } else {
            count = redisTemplate.opsForSet().size(redisKey);
        }

        model.addAttribute("cartCount", count);
    }

    public static List<String> parsingCart(String cart) {
        return Arrays.stream(cart.split("/")).collect(Collectors.toList());
    }

    /**
     * 현재 장바구니에서 삭제한 상품을 처리할 메서드.
     *
     * @param redisKey  Redis Key 값
     * @param productNo 삭제된 상품 번호
     */
    public void deleteProductInCart(String redisKey, Long productNo) {
        if (Objects.nonNull(redisTemplate.opsForSet().size(redisKey))) {
            redisTemplate.opsForSet().remove(redisKey, productNo);
        }
    }

}
