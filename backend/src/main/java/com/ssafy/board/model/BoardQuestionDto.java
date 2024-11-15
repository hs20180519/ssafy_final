package com.ssafy.board.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardQuestionDto {
	private int id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int viewCount;
}
