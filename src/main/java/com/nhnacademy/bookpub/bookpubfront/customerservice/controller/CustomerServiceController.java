package com.nhnacademy.bookpub.bookpubfront.customerservice.controller;

import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.CreateCustomerServiceRequestDto;
import com.nhnacademy.bookpub.bookpubfront.customerservice.dto.GetCustomerServiceListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.customerservice.service.CustomerServiceService;
import com.nhnacademy.bookpub.bookpubfront.state.CustomerServiceState;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
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
@RequestMapping
public class CustomerServiceController {
    private final CustomerServiceService customerServiceService;

    @GetMapping("/admin/services")
    public String viewAdminCustomerService(Model model, Pageable pageable) {
        PageResponse<GetCustomerServiceListResponseDto> services =
                customerServiceService.getCustomerServices(pageable);

        setServicesInModel(model, services);

        return "/admin/customerservice/adminCustomerServiceMain";
    }

    @PostMapping("/admin/services")
    public String createCustomerService(
            @RequestPart CreateCustomerServiceRequestDto request,
            @RequestPart(required = false) MultipartFile image) {
        customerServiceService.createCustomerService(request, image);

        return "redirect:/admin/services";
    }

    @GetMapping("/services/faq/{category}")
    public String viewCustomerServiceFAQ(@PathVariable(required = false) String category,
                                         @PageableDefault Pageable pageable,
                                         Model model) {
        PageResponse<GetCustomerServiceListResponseDto> services;

        if (category == null) {
            services = customerServiceService
                    .getCustomerServiceByCodeName(CustomerServiceState.FAQ.getName(), pageable);
        } else {
            services = customerServiceService
                    .getCustomerServiceByCategory(category, pageable);
        }

        setServicesInModel(model, services);

        return "/customerservice/customerServiceFAQ";
    }

    @GetMapping("/services/notice/{category}")
    public String viewCustomerServiceNotice(@PathVariable(required = false) String category,
                                           @PageableDefault Pageable pageable,
                                           Model model) {
        PageResponse<GetCustomerServiceListResponseDto> services;

        if (category == null) {
            services = customerServiceService
                    .getCustomerServiceByCodeName(CustomerServiceState.NOTICE.getName(), pageable);
        } else {
            services = customerServiceService
                    .getCustomerServiceByCategory(category, pageable);
        }

        setServicesInModel(model, services);

        return "/customerservice/customerServiceFAQ";
    }

    /**
     * 고객서비스들을 모델에 세팅하는 메소드입니다.
     *
     * @param model 모델
     * @param services 서비스 페이지
     */
    private static void setServicesInModel(Model model, PageResponse<GetCustomerServiceListResponseDto> services) {
        model.addAttribute("services", services.getContent());
        model.addAttribute("totalPages", services.getTotalPages());
        model.addAttribute("currentPage", services.getNumber());
        model.addAttribute("isNext", services.isNext());
        model.addAttribute("isPrevious", services.isPrevious());
        model.addAttribute("pageButtonNum", 5);
    }
}
