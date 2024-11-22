package com.ssafy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoApiUtil {

	@Value("${kakao.api_url}")
	private String KAKAO_API_URL;

	@Value("${kakao.api_key}")
	private String KAKAO_API_KEY;

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public KakaoApiUtil() {
		this.restTemplate = new RestTemplate();
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * 주소로 위도와 경도를 조회합니다.
	 *
	 * @param address 조회할 주소
	 * @return 위도와 경도를 포함한 Location 객체
	 * @throws Exception JSON 파싱 오류 발생 시
	 */
	public Location getCoordinates(String address) throws Exception {
		// HTTP 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);

		// API 호출 URL
		String url = KAKAO_API_URL + "?query=" + address;

		// 요청 보내기
		HttpEntity<Void> request = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url,
			org.springframework.http.HttpMethod.GET,
			request,
			String.class);

		// 응답 결과 파싱
		JsonNode root = objectMapper.readTree(response.getBody());
		JsonNode documents = root.path("documents");

		if (documents.isEmpty()) {
			throw new Exception("주소를 찾을 수 없습니다.");
		}

		// 첫 번째 결과에서 위도와 경도 추출
		JsonNode firstResult = documents.get(0);
		double latitude = firstResult.path("y").asDouble();
		double longitude = firstResult.path("x").asDouble();

		return new Location(latitude, longitude);
	}

	// 위도와 경도를 담는 객체
	public static class Location {
		private double latitude;
		private double longitude;

		public Location(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public double getLatitude() {
			return latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		@Override
		public String toString() {
			return "Location{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				'}';
		}
	}

	public double calculateDistance(String address1, String address2) throws Exception {
		// 두 주소의 좌표를 가져오는 로직
		Location location1 = getCoordinates(address1);
		Location location2 = getCoordinates(address2);

		// 두 좌표 간 거리 계산
		return calculateHaversineDistance(location1, location2);
	}

	// Haversine 공식을 사용해 두 지점 사이의 거리를 계산
	private double calculateHaversineDistance(Location loc1, Location loc2) {
		final double R = 6371.0; // 지구 반지름 (단위: km)
		double lat1 = Math.toRadians(loc1.getLatitude());
		double lon1 = Math.toRadians(loc1.getLongitude());
		double lat2 = Math.toRadians(loc2.getLatitude());
		double lon2 = Math.toRadians(loc2.getLongitude());

		double dlat = lat2 - lat1;
		double dlon = lon2 - lon1;

		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
			+ Math.cos(lat1) * Math.cos(lat2)
				* Math.sin(dlon / 2) * Math.sin(dlon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c; // km 단위로 반환
	}

}
