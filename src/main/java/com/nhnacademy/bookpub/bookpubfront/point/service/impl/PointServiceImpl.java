package com.nhnacademy.bookpub.bookpubfront.point.service.impl;

import com.nhnacademy.bookpub.bookpubfront.point.adaptor.PointAdaptor;
import com.nhnacademy.bookpub.bookpubfront.point.dto.request.PointGiftRequestDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointResponseDto;
import com.nhnacademy.bookpub.bookpubfront.point.service.PointService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 포인트 서비스 구현체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointAdaptor pointAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPointResponseDto> getMemberPointHistory(
            Pageable pageable, Long memberNo, String type) {
        return pointAdaptor.getMemberPointHistory(pageable, memberNo, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void giftPoint(Long memberNo, PointGiftRequestDto requestDto) {
        pointAdaptor.giftPoint(memberNo, requestDto);
    }
}
