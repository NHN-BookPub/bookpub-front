package com.nhnacademy.bookpub.bookpubfront.admin.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberTierStatisticsResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.OrderCntResponseDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.SaleProductCntDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleYearDto;
import com.nhnacademy.bookpub.bookpubfront.sales.service.SalesService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin을 위한 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final SalesService salesService;

    /**
     * 어드민 페이지.
     *
     * @param model 모델.
     * @return 어드민 페이지.
     */
    @Auth
    @GetMapping
    public String adminIndex(Model model) {
        MemberStatisticsResponseDto memberStatisticsResponseDto = memberService.memberStatistics();
        List<MemberTierStatisticsResponseDto> tierStatistics = memberService.memberTierStatistics();
        List<TotalSaleDto> monthSales = salesService.getSales(null, null);
        List<OrderCntResponseDto> orderCnt = salesService.getOrderCnt();
        List<TotalSaleYearDto> yearSales =
                salesService.getOrderYear(getFirstDayOfYear(), LocalDateTime.now());
        List<SaleProductCntDto> saleProductCnt = salesService.getSaleProductRankCount(
                getFirstDayOfYear(), LocalDateTime.now());

        model.addAttribute("orderCount", orderCnt);
        model.addAttribute("monthSales", monthSales);
        model.addAttribute("yearSales", yearSales);
        model.addAttribute("memberStatistics", memberStatisticsResponseDto);
        model.addAttribute("tierStatistics", tierStatistics);
        model.addAttribute("saleProductCounts", saleProductCnt);

        return "admin/index";
    }

    /**
     * 올해의 1월 1일 0시 0분 0초를 구하는 메서드입니다.
     *
     * @return 1월 1일 0시 0분 0초 시간정보
     */
    private LocalDateTime getFirstDayOfYear() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withDayOfYear(1);
    }
}
