package com.nhnacademy.bookpub.bookpubfront.elastic.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 통합 검(상품, 고객서비스(cs)) 두개의 인덱스를 조회하는 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@Setter
public class AllHit {
    private List<Hits> hits;

    @Getter
    @Setter
    public static class Hits {
        private List<AllInfo> _source = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class AllInfo {
        private Long id;
        private String title;
        private Long salesprice;
        private Integer salesrate;
        private String filepath;
        private Integer csid;
        private String cscodename;
        private String cstitle;
        private String cscategory;
        private LocalDateTime csdate;
    }
}
