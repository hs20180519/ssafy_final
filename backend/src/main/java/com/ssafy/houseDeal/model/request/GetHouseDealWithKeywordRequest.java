package com.ssafy.houseDeal.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetHouseDealWithKeywordRequest {
    private String type;
    private String code;
    private String sort;
    private int pgSize;
    private int pgno;
    private int offset;
}
