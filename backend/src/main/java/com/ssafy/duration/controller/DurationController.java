package com.ssafy.duration.controller;

import com.ssafy.duration.model.request.TMapDurationRequest;
import com.ssafy.duration.model.response.DurationResponse;
import com.ssafy.duration.model.response.TransitApiResponse;
import com.ssafy.duration.service.DurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/duration")
@RequiredArgsConstructor
public class DurationController {
    private final DurationService durationService;
    @GetMapping("/car")
    public ResponseEntity<Mono<DurationResponse>> getCarDuration(
            @RequestParam("startX") float startX,
            @RequestParam("startY") float startY,
            @RequestParam("endX") float endX,
            @RequestParam("endY") float endY
    ) {

        return ResponseEntity.ok(
                durationService.getCarDuration(
                        TMapDurationRequest.builder()
                                .startX(startX)
                                .startY(startY)
                                .endX(endX)
                                .endY(endY)
                                .build()
                        )
                );
    }

    @GetMapping("/walk")
    public ResponseEntity<Mono<DurationResponse>> getWalkDuration(
            @RequestParam("startX") float startX,
            @RequestParam("startY") float startY,
            @RequestParam("endX") float endX,
            @RequestParam("endY") float endY,
            @RequestParam("startName") String startName,
            @RequestParam("endName") String endName
    ) {
        return ResponseEntity.ok(
                durationService.getWalkDuration(
                        TMapDurationRequest.builder()
                                .startX(startX)
                                .startY(startY)
                                .endX(endX)
                                .endY(endY)
                                .startName(startName)
                                .endName(endName)
                                .build()
                )
        );
    }

    @GetMapping("/transit")
    public ResponseEntity<Mono<DurationResponse>> getTransitDuration(
            @RequestParam("startX") float startX,
            @RequestParam("startY") float startY,
            @RequestParam("endX") float endX,
            @RequestParam("endY") float endY

    ) {
        return ResponseEntity.ok(
                durationService.getTransitDuration(
                        TMapDurationRequest.builder()
                                .startX(startX)
                                .startY(startY)
                                .endX(endX)
                                .endY(endY)
                                .build()
                )
        );
    }
}
