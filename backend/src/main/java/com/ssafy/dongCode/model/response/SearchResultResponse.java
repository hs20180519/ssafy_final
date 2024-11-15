package com.ssafy.dongCode.model.response;

import com.ssafy.dongCode.model.DongCodeDto;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class SearchResultResponse {
    private List<DongCodeDto> regions;

    private List<HouseDealSearchResult> houses;
}
