package com.ssafy.duration.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CarApiResponse {
    private String type;
    private List<Feature> features;

    @Data
    public static class Feature {
        private String type;
//        private Geometry geometry;
        private Properties properties;

        //필요한 데이터만 선언
//        @Data
//        public static class Geometry {
//            private String type;
//            private Object coordinates;
//        }

        @Data
        public static class Properties {
            private int totalDistance;
            private int totalTime;
            private int totalFare;
        }
    }
}
