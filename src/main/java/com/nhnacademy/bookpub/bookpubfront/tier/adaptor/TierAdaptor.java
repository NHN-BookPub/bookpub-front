package com.nhnacademy.bookpub.bookpubfront.tier.adaptor;

import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import java.util.List;

/**
 * Tier 가 api 서버완 연동하기위한 인터페이스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/

public interface TierAdaptor {
    /**
     * 등급생성요청을하는 메서드입니다.
     *
     * @param createRequestDto 등급의 생성정보가 기입
     */
    void requestAddTier(CreateTierRequestDto createRequestDto);

    /**
     * 등급의 정보를 수정하기위한 메서드입니다.
     *
     * @param modifyTierRequestDto 등급의 수정정보가 기입
     */
    void requestModifyTier(ModifyTierRequestDto modifyTierRequestDto);

    /**
     * 등급들의 정보를 받기위한 요청입니다.
     *
     * @return the list
     */
    List<TierResponseDto> requestTierList();

    /**
     * 등급의 번호를 통해 등급을 불러오기위한 메서드입니다.
     *
     * @param tierNo 등급번호
     * @return 등급정보가 반환됩니다.
     */
    TierResponseDto requestTier(Integer tierNo);

    /**
     * 등급의 존재유무를 알려주는 메서드입니다.
     *
     * @param tierName 등급이름이 반환됩니다.
     * @return boolean 값이 반환됩니다.
     */
    Boolean requestTierName(String tierName);
}
