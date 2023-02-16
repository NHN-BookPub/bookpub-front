package com.nhnacademy.bookpub.bookpubfront.sales.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleDto;
import com.nhnacademy.bookpub.bookpubfront.sales.service.SalesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 매출관련 컨트롤러.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class SaleRestController {
    private final SalesService salesService;

    @Auth
    @GetMapping("/sales")
    public ResponseEntity<List<TotalSaleDto>> sales() {
        return ResponseEntity
                .ok(salesService.getSales(null, null));
    }
}
