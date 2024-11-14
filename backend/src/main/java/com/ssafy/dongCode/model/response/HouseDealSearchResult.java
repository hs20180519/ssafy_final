package com.ssafy.dongCode.model.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HouseDealSearchResult {
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String aptSeq;
    private String aptNm;
}
