package com.ssafy.chat.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

	@Value("${gpt.api_key}")
	private String apiKey;

	@Value("${gpt.api_url}")
	private String apiUrl;

	@Override
	public String getChatResponse(String message) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(apiKey);

			// Request Body 생성
			Map<String, Object> requestBody = new HashMap<>();
			requestBody.put("model", "gpt-3.5-turbo");
			requestBody.put("messages", List.of(Map.of("role", "user", "content", message)));

			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

			ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

			// 응답 처리
			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				Map<String, Object> body = response.getBody();
				List<Map<String, Object>> choices = (List<Map<String, Object>>)body.get("choices");

				if (choices != null && !choices.isEmpty()) {
					Map<String, Object> messageMap = (Map<String, Object>)choices.get(0).get("message");
					return (String)messageMap.get("content");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred while processing your request.";
		}

		throw new RuntimeException("Failed to get a valid response from the API.");
	}
}
