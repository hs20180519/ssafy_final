package com.ssafy.board.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardAnswerDto {
	private int id;
	private int questionId;
	private String content;
	private String author;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
