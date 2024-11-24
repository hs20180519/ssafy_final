package com.ssafy.duration.model.response;

import lombok.Data;
import java.util.List;

@Data
public class TransitApiResponse {

    private MetaData metaData;

    @Data
    public static class MetaData {
        private RequestParameters requestParameters;
        private Plan plan;

        @Data
        public static class RequestParameters {
            private String endY;
            private String endX;
            private String startY;
            private String startX;
            private String reqDttm;
        }

        @Data
        public static class Plan {
            private List<Itinerary> itineraries;

            @Data
            public static class Itinerary {
                private Fare fare;
                private int totalTime;
                private int totalWalkTime;
                private int pathType;
                private int transferCount;
                private int totalDistance;
                private int totalWalkDistance;

                @Data
                public static class Fare {
                    private Regular regular;

                    @Data
                    public static class Regular {
                        private int totalFare;
                        private Currency currency;

                        @Data
                        public static class Currency {
                            private String symbol;
                            private String currency;
                            private String currencyCode;
                        }
                    }
                }
            }
        }
    }
}
