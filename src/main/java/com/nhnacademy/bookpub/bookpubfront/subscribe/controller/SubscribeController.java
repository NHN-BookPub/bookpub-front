package com.nhnacademy.bookpub.bookpubfront.subscribe.controller;

import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FEE;
import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FREE_FEE_STANDARD;
import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.ModifySubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.service.SubscribeService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 구독관련 컨트롤러 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService service;
    private final ProductService productService;
    private final ReviewService reviewService;

    private static final String RE_SUBSCRIBE = "redirect:/admin/subscribes";

    /**
     * 구독의 관리자 메인페이지로 갑니다.
     *
     * @param pageable 페이지
     * @param model    모델
     * @return 관리자페이지 string
     */
    @GetMapping("/admin/subscribes")
    public String subscribeMain(@PageableDefault Pageable pageable,
                                Model model) {

        PageResponse<GetSubscribeResponseDto> subscribes = service.getSubscribeList(pageable);

        model.addAttribute("content", subscribes.getContent());
        model.addAttribute("next", subscribes.isNext());
        model.addAttribute("previous", subscribes.isPrevious());
        model.addAttribute("totalPage", subscribes.getTotalPages());
        model.addAttribute("pageNum", subscribes.getNumber());
        model.addAttribute("previousPageNo", subscribes.getNumber() - 1);
        model.addAttribute("nextPageNo", subscribes.getNumber() + 1);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("uri", "/admin/subscribes");

        return "admin/subscribe/subscribeList";
    }

    /**
     * 구독등록이후 리다이렉트.
     *
     * @param dto   생성정보
     * @param image 이미지 값.
     * @return 관리자 구독 메인페이지로 리다이렉트
     */
    @Auth
    @PostMapping("/admin/subscribes")
    public String subscribeAdd(@ModelAttribute CreateSubscribeRequestDto dto,
                               @RequestPart MultipartFile image) {
        service.addSubscribe(dto, image);

        return RE_SUBSCRIBE;
    }

    /**
     * 구독수정
     *
     * @param dto         구독수정정보.
     * @param image       이미지파일
     * @param subscribeNo 구독번호
     * @return the string
     */
    @Auth
    @PostMapping("/admin/subscribes/{subscribeNo}")
    public String subscribeModify(@ModelAttribute ModifySubscribeRequestDto dto,
                                  @RequestPart MultipartFile image,
                                  @PathVariable("subscribeNo") Long subscribeNo) {
        service.modifySubscribe(subscribeNo, image, dto);
        return "redirect:/admin/subscribe/" + subscribeNo;
    }


    /**
     * 구독상세조회
     *
     * @param subscribeNo 구독번호
     * @param model       모델
     * @return the string
     */
    @GetMapping("/admin/subscribes/{subscribeNo}")
    public String subscribeDetails(@PathVariable("subscribeNo") Long subscribeNo,
                                   Model model) {
        GetSubscribeDetailResponseDto subscribe = service.getSubscribeDetail(subscribeNo);
        model.addAttribute("subscribe", subscribe);
        model.addAttribute("content", subscribe.getProductLists());

        return "admin/subscribe/subscribesDetail";
    }

    /**
     * 구독삭제
     *
     * @param subscribeNo 구독번호
     * @param deleted     삭제여부
     * @return the string
     */
    @Auth
    @PostMapping("/admin/subscribes-removed/{subscribeNo}")
    public String subscribeDelete(@PathVariable("subscribeNo") Long subscribeNo,
                                  @RequestParam("deleted") boolean deleted) {
        service.deleteSubscribe(subscribeNo, deleted);
        return RE_SUBSCRIBE;
    }

    /**
     * 갱신여부를 변경합니다.
     *
     * @param subscribeNo 구독번호.
     * @param renewed     갱신여부 변경
     * @return the string
     */
    @Auth
    @PostMapping("/admin/subscribes-renewed/{subscribeNo}")
    public String subscribeRenewed(@PathVariable("subscribeNo") Long subscribeNo,
                                   @RequestParam("renewed") boolean renewed) {
        service.renewedSubscribe(subscribeNo, renewed);
        return RE_SUBSCRIBE;
    }

    /**
     * 구독내부상품리스트.
     *
     * @param subscribeNo 구독번호.
     * @param products    구독상품번호들.
     * @return the string
     */
    @Auth
    @PostMapping("/admin/subscribes-product-list/{subscribeNo}")
    public String subscribeProductList(@PathVariable("subscribeNo") Long subscribeNo,
                                       @RequestParam("products") String products) {
        log.error(products);
        service.addSubscribeProductList(subscribeNo, products);
        return "redirect:/admin/subscribes/" + subscribeNo;
    }

    /**
     * 메인구독페이지
     *
     * @param pageable 페이징.
     * @param model    모델
     * @return the string
     */
    @GetMapping("/subscribes")
    public String mainSubscribes(@PageableDefault Pageable pageable,
                                 Model model) {

        PageResponse<GetSubscribeResponseDto> subscribes = service.getSubscribeList(pageable);
        model.addAttribute("content", subscribes.getContent());
        model.addAttribute("next", subscribes.isNext());
        model.addAttribute("previous", subscribes.isPrevious());
        model.addAttribute("totalPage", subscribes.getTotalPages());
        model.addAttribute("pageNum", subscribes.getNumber());
        model.addAttribute("previousPageNo", subscribes.getNumber() - 1);
        model.addAttribute("nextPageNo", subscribes.getNumber() + 1);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("uri", "/subscribes");
        return "subscription/subscribeMain";
    }

    /**
     * 구독상세페이지
     *
     * @param subscribeNo 구독번호
     * @param model       the model
     * @return the string
     */
    @GetMapping("/subscribes/{subscribeNo}")
    public String subscribeDetail(@PathVariable("subscribeNo") Long subscribeNo,
                                  Model model) {

        GetSubscribeDetailResponseDto subscribe = service.getSubscribeDetail(subscribeNo);
        Long productNo = subscribe.getProductLists().get(0).getProductNo();
        GetProductDetailResponseDto product = productService.findProduct(productNo);

        model.addAttribute("subscribe", subscribe);
        model.addAttribute("product", product);
        model.addAttribute("free", DELIVERY_FREE_FEE_STANDARD.getFee());
        model.addAttribute("deliveryFee", DELIVERY_FEE.getFee());
        model.addAttribute("reviewInfo", reviewService.getProductReviewInfo(productNo));
        return "subscription/subscribeDetail";
    }
}
