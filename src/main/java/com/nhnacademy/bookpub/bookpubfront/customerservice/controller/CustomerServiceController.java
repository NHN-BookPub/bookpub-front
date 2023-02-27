package com.nhnacademy.bookpub.bookpubfront.customerservice.controller;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.CreateCustomerServiceRequestDto;
import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.GetCustomerServiceListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.customerservice.service.CustomerServiceService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.state.CustomerServiceState;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 고객서비스의 컨트롤러.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class CustomerServiceController {
    private final CustomerServiceService customerServiceService;
    private final MemberUtils memberUtils;
    private final CategoryUtils categoryUtils;
    private final CartUtils cartUtils;

    /**
     * 고객서비스 전체 조회.
     *
     * @param model    모델
     * @param pageable 페이징
     * @return 고객서비스 전체
     */
    @GetMapping("/admin/services")
    public String viewAdminCustomerService(Model model,
                                           @PageableDefault Pageable pageable) {
        PageResponse<GetCustomerServiceListResponseDto> services =
                customerServiceService.getCustomerServices(pageable);

        for (GetCustomerServiceListResponseDto responseDto : services.getContent()) {
            setCategoryEngToKor(responseDto);
        }

        setServicesInModel(model, services);
        memberUtils.modelRequestMemberNo(model);

        return "admin/customerservice/adminCustomerServiceMain";
    }

    /**
     * 고객서비스를 생성합니다.
     *
     * @param request 생성시 필요한 dto
     * @param image   이미지
     * @return 고객서비스 뷰
     */
    @PostMapping("/admin/services")
    public String createCustomerService(
            @Valid @ModelAttribute CreateCustomerServiceRequestDto request,
            @RequestPart(required = false) MultipartFile image) {

        customerServiceService.createCustomerService(request, image);

        return "redirect:/admin/services";
    }

    /**
     * 고객서비스 단건조회(관리자).
     *
     * @param serviceNo 서비스번호
     * @param model     모델
     * @return 단건반환
     */
    @GetMapping("/admin/service/{serviceNo}")
    public String getCustomerService(@PathVariable Integer serviceNo, Model model) {
        GetCustomerServiceListResponseDto response =
                customerServiceService.getCustomerServiceByNo(serviceNo);

        setCategoryEngToKor(response);

        model.addAttribute("service", response);

        return "admin/customerservice/adminCustomerServiceView";
    }

    /**
     * 고객서비스를 삭제합니다.
     *
     * @param serviceNo 서비스번호
     * @return 서비스뷰
     */
    @GetMapping("/admin/services/{serviceNo}")
    public String deleteCustomerService(@PathVariable Integer serviceNo) {
        customerServiceService.deleteCustomerService(serviceNo);

        return "redirect:/admin/services";
    }

    /**
     * FAQ를 조회합니다.
     *
     * @param category 카테고리
     * @param pageable 페이징
     * @param model    모델
     * @return FAQ
     */
    @GetMapping("/services/faq")
    public String viewCustomerServiceFAQ(@RequestParam(required = false) String category,
                                         @PageableDefault Pageable pageable,
                                         @CookieValue(name = CartUtils.CART_COOKIE) String key,
                                         Model model) {
        PageResponse<GetCustomerServiceListResponseDto> services;

        if (category == null) {
            services = customerServiceService
                    .getCustomerServiceByCodeName(CustomerServiceState.FAQ.getName(), pageable);
            model.addAttribute("paramValue", null);
        } else {
            services = customerServiceService
                    .getCustomerServiceByCategory(category, pageable);
            model.addAttribute("paramValue", category);
        }

        for (GetCustomerServiceListResponseDto responseDto : services.getContent()) {
            setCategoryEngToKor(responseDto);
        }

        cartUtils.getCountInCart(key, model);
        memberUtils.modelRequestMemberNo(model);
        categoryUtils.categoriesView(model);
        setServicesInModel(model, services);

        return "customerservice/customerServiceFAQ";
    }

    /**
     * 카테고리별로 고객서비스를 조회합니다.
     *
     * @param category 카테고리
     * @param pageable 페이징
     * @param model    모델
     * @return 고객서비스
     */
    @GetMapping("/services/notice")
    public String viewCustomerServiceNotice(@RequestParam(required = false) String category,
                                            @PageableDefault Pageable pageable,
                                            @CookieValue(name = CartUtils.CART_COOKIE) String key,
                                            Model model) {
        PageResponse<GetCustomerServiceListResponseDto> services;

        if (category == null) {
            services = customerServiceService
                    .getCustomerServiceByCodeName(CustomerServiceState.NOTICE.getName(), pageable);
            model.addAttribute("paramValue", null);
        } else {
            services = customerServiceService
                    .getCustomerServiceByCategory(category, pageable);
            model.addAttribute("paramValue", category);
        }

        for (GetCustomerServiceListResponseDto responseDto : services.getContent()) {
            setCategoryEngToKor(responseDto);
        }

        cartUtils.getCountInCart(key, model);
        memberUtils.modelRequestMemberNo(model);
        categoryUtils.categoriesView(model);
        setServicesInModel(model, services);

        return "customerservice/customerServiceNotification";
    }

    /**
     * 고객서비스 단건 조회 뷰입니다.(공지사항)
     *
     * @param no    서비스 번호
     * @param model 모델
     * @return 단건
     */
    @GetMapping("/service/notice")
    public String viewCustomerServiceNotice(@RequestParam(name = "no") Integer no, Model model) {
        GetCustomerServiceListResponseDto response = customerServiceService.getCustomerServiceByNo(no);

        setCategoryEngToKor(response);
        model.addAttribute("service", response);

        return "customerservice/customerServiceNoticeView";
    }

    /**
     * 고객서비스 단건 조회 뷰입니다.(FAQ)
     *
     * @param no    서비스 번호
     * @param model 모델
     * @return 단건
     */
    @GetMapping("/service/faq")
    public String viewCustomerServiceFAQ(@RequestParam(name = "no") Integer no, Model model,
                                         @CookieValue(name = CartUtils.CART_COOKIE) String key) {
        GetCustomerServiceListResponseDto response = customerServiceService.getCustomerServiceByNo(no);

        setCategoryEngToKor(response);

        cartUtils.getCountInCart(key, model);
        memberUtils.modelRequestMemberNo(model);
        categoryUtils.categoriesView(model);
        model.addAttribute("service", response);

        return "customerservice/customerServiceFAQView";
    }

    /**
     * 고객서비스들을 모델에 세팅하는 메소드입니다.
     *
     * @param model    모델
     * @param services 서비스 페이지
     */
    private void setServicesInModel(Model model, PageResponse<GetCustomerServiceListResponseDto> services) {
        model.addAttribute("services", services.getContent());
        model.addAttribute("totalPages", services.getTotalPages());
        model.addAttribute("currentPage", services.getNumber());
        model.addAttribute("isNext", services.isNext());
        model.addAttribute("isPrevious", services.isPrevious());
        model.addAttribute("pageButtonNum", 5);
    }

    /**
     * 영어로 저장된 카테고리를 한글로 변환합니다.
     *
     * @param response 변환할 dto
     */
    private void setCategoryEngToKor(GetCustomerServiceListResponseDto response) {
        switch (response.getServiceCategory()) {
            case "faqUsing":
                response.setCategory("이용안내");
                break;
            case "faqAccount":
                response.setCategory("계정안내");
                break;
            case "faqPayment":
                response.setCategory("결제안내");
                break;
            case "faqOthers":
                response.setCategory("기타안내");
                break;
            case "noteNormal":
                response.setCategory("일반");
                break;
            case "noteServer":
                response.setCategory("서버");
                break;
            case "notePayment":
                response.setCategory("결제");
                break;
            case "noteOthers":
                response.setCategory("기타");
                break;
            default:
                response.setCategory("에러");
        }
    }
}
