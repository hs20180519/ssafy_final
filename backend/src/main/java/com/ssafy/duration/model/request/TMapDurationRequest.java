package com.ssafy.duration.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TMapDurationRequest {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private String startAddress;
    private String startName;
    private String endName;
}
