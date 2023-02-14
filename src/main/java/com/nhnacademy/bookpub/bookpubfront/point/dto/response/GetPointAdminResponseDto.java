package com.nhnacademy.bookpub.bookpubfront.point.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPointAdminResponseDto {
    private String memberId;
    private Long pointHistoryAmount;
    private String pointHistoryReason;
    private LocalDateTime createdAt;
    private boolean increased;
}