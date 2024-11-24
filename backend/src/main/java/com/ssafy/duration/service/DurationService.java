package com.ssafy.duration.service;

import com.ssafy.duration.model.request.TMapDurationRequest;
import com.ssafy.duration.model.response.DurationResponse;
import reactor.core.publisher.Mono;

public interface DurationService {
    public Mono<DurationResponse> getCarDuration(TMapDurationRequest request) throws Exception;

    public Mono<DurationResponse> getWalkDuration(TMapDurationRequest request);

    public Mono<DurationResponse> getTransitDuration(TMapDurationRequest request);
}
