package com.ssafy.duration.service;

import com.ssafy.duration.model.request.TMapDurationRequest;
import com.ssafy.duration.model.response.CarApiResponse;
import com.ssafy.duration.model.response.DurationResponse;
import com.ssafy.duration.model.response.TransitApiResponse;
import com.ssafy.duration.model.response.WalkApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DurationServiceImpl implements DurationService {

    @Value("${tmap.app_key}")
    private String APP_KEY;

    private static final String TMAP_BASE_URL = "https://apis.openapi.sk.com/tmap";
    private static final String TMAP_TRANSIT_BASE_URL = "https://apis.openapi.sk.com/transit";

    @Override
    public Mono<DurationResponse> getCarDuration(TMapDurationRequest request) {
        WebClient webClient = WebClient.builder()
                .baseUrl(TMAP_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<CarApiResponse> response = webClient.post()
                .uri("/routes")
//                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), TMapDurationRequest.class)
                .header("appKey", APP_KEY)
                .retrieve()
                .bodyToMono(CarApiResponse.class);

        return response.map(apiResponse -> {
            int totalTime = apiResponse.getFeatures().get(0).getProperties().getTotalTime();

            return DurationResponse.builder()
                    .totalTime(totalTime)
                    .build();
        });

    }

    @Override
    public Mono<DurationResponse> getWalkDuration(TMapDurationRequest request) {
        // TODO: webClient build 분리설계
        WebClient webClient = WebClient.builder()
                .baseUrl(TMAP_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<WalkApiResponse> response = webClient.post()
                .uri("/routes/pedestrian")
//                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), TMapDurationRequest.class)
                .header("appKey", APP_KEY)
                .retrieve()
                .bodyToMono(WalkApiResponse.class);

        return response.map(apiResponse -> {
            int totalTime = apiResponse.getFeatures().get(0).getProperties().getTotalTime();

            return DurationResponse.builder()
                    .totalTime(totalTime)
                    .build();
        });
    }

    @Override
    public Mono<DurationResponse> getTransitDuration(TMapDurationRequest request) {
        WebClient webClient = WebClient.builder()
                .baseUrl(TMAP_TRANSIT_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<TransitApiResponse> response = webClient.post()
                .uri("/routes/sub")
                .body(Mono.just(request), TMapDurationRequest.class)
                .header("appKey", APP_KEY)
                .retrieve()
                .bodyToMono(TransitApiResponse.class);

        return response.map(apiResponse -> {
            List<TransitApiResponse.MetaData.Plan.Itinerary> itineraries = apiResponse.getMetaData().getPlan().getItineraries();

            TransitApiResponse.MetaData.Plan.Itinerary minTimeItinerary = itineraries.stream()
                    .min(Comparator.comparingInt(TransitApiResponse.MetaData.Plan.Itinerary::getTotalTime))
                    .orElseThrow(() -> new IllegalStateException("No Itineraries Found"));

            int minTotalTime = minTimeItinerary.getTotalTime();

            return DurationResponse.builder()
                    .totalTime(minTotalTime)
                    .build();
        });
    }


}
