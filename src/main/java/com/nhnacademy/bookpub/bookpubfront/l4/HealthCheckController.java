package com.nhnacademy.bookpub.bookpubfront.l4;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check 를 하기위한 컨트롤러 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@RestController
@RequestMapping
public class HealthCheckController {
    private boolean healthCheck = true;

    @GetMapping("/monitor/l7check")
    public ResponseEntity<String> getHealth() {
        if (healthCheck) {
            return ResponseEntity.ok()
                    .body("Health Check OK");
        }

        return ResponseEntity.badRequest()
                .build();
    }

    @GetMapping("/deploy/ready")
    public void getHealthFail() {
        healthCheck = false;
    }
}
