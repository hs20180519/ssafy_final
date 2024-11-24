package com.ssafy.duration.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TMapDurationRequest {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private String startName;
    private String endName;
}
