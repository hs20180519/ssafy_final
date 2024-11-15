package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.BoardAnswerDto;
import com.ssafy.board.model.BoardQuestionDto;
import com.ssafy.board.model.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 질문 CRUD

	// 모든 질문 조회
	@GetMapping("/questions")
	public List<BoardQuestionDto> getAllQuestions() {
		return boardService.getAllQuestions();
	}

	// 질문 상세 조회
	@GetMapping("/questions/{id}")
	public BoardQuestionDto getQuestionById(@PathVariable
	int id) {
		return boardService.getQuestionById(id);
	}

	// 질문 등록
	@PostMapping("/questions")
	public void createQuestion(@RequestBody
	BoardQuestionDto question) {
		boardService.createQuestion(question);
	}

	// 질문 수정
	@PutMapping("/questions/{id}")
	public void updateQuestion(@PathVariable
	int id, @RequestBody
	BoardQuestionDto question) {
		question.setId(id);
		boardService.updateQuestion(question);
	}

	// 질문 삭제
	@DeleteMapping("/questions/{id}")
	public void deleteQuestion(@PathVariable
	int id) {
		boardService.deleteQuestion(id);
	}

	// 답변 CRUD

	// 특정 질문에 대한 모든 답변 조회
	@GetMapping("/questions/{questionId}/answers")
	public List<BoardAnswerDto> getAnswersByQuestionId(@PathVariable
	int questionId) {
		return boardService.getAnswersByQuestionId(questionId);
	}

	// 답변 등록
	@PostMapping("/questions/{questionId}/answers")
	public void createAnswer(@PathVariable
	int questionId, @RequestBody
	BoardAnswerDto answer) {
		answer.setQuestionId(questionId);
		boardService.createAnswer(answer);
	}

	// 답변 수정
	@PutMapping("/answers/{id}")
	public void updateAnswer(@PathVariable
	int id, @RequestBody
	BoardAnswerDto answer) {
		answer.setId(id);
		boardService.updateAnswer(answer);
	}

	// 답변 삭제
	@DeleteMapping("/answers/{id}")
	public void deleteAnswer(@PathVariable
	int id) {
		boardService.deleteAnswer(id);
	}
}
