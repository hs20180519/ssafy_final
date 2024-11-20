package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
	public ResponseEntity<List<BoardQuestionDto>> getAllQuestions() {
		List<BoardQuestionDto> questions = boardService.getAllQuestions();
		return ResponseEntity.ok(questions);
	}

	// 질문 상세 조회
	@GetMapping("/questions/{id}")
	public ResponseEntity<BoardQuestionDto> getQuestionById(@PathVariable
	int id) {
		BoardQuestionDto question = boardService.getQuestionById(id);
		if (question != null) {
			return ResponseEntity.ok(question);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// 질문 등록
	@PostMapping("/questions")
	public ResponseEntity<String> createQuestion(@RequestBody
	BoardQuestionDto question, Authentication authentication) {
		try {
			question.setAuthor((String)authentication.getPrincipal());
			System.out.println(authentication.getPrincipal());
			//			question.setAuthor(authentication.getPrincipal());
			boardService.createQuestion(question);
			return ResponseEntity.status(HttpStatus.CREATED).body("질문이 등록되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("질문 등록 중 오류가 발생했습니다.");
		}
	}

	// 질문 수정
	@PutMapping("/questions/{id}")
	public ResponseEntity<String> updateQuestion(@PathVariable
	int id, @RequestBody
	BoardQuestionDto question,
		Authentication authentication) {
		try {

			// 질문을 조회하여 작성자 정보 가져오기
			BoardQuestionDto existingQuestion = boardService.getQuestionById(id);

			// 작성자와 현재 로그인된 사용자 비교
			if (!existingQuestion.getAuthor().equals(authentication.getPrincipal())
				&& !authentication.getAuthorities().contains("ROLE_admin")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("작성자가 아니므로 수정할 수 없습니다.");
			}

			question.setId(id);
			boardService.updateQuestion(question);
			return ResponseEntity.ok("질문이 수정되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("질문 수정 중 오류가 발생했습니다.");
		}
	}

	// 질문 삭제
	@DeleteMapping("/questions/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable
	int id, Authentication authentication) {
		try {
			// 질문을 조회하여 작성자 정보 가져오기
			BoardQuestionDto existingQuestion = boardService.getQuestionById(id);

			// 작성자와 현재 로그인된 사용자 비교
			if (!existingQuestion.getAuthor().equals(authentication.getPrincipal())
				&& !authentication.getAuthorities().contains("ROLE_admin")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("작성자가 아니므로 수정할 수 없습니다.");
			}
<<<<<<< HEAD
=======

>>>>>>> 64013a8 (:zap: Fix: 질문 삭제시시 작성자와 현재 로그인된 사용자 비교)
			boardService.deleteQuestion(id);
			return ResponseEntity.ok("질문이 삭제되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("질문 삭제 중 오류가 발생했습니다.");
		}
	}

	// 답변 CRUD

	// 특정 질문에 대한 모든 답변 조회
	@GetMapping("/questions/{questionId}/answers")
	public ResponseEntity<List<BoardAnswerDto>> getAnswersByQuestionId(@PathVariable
	int questionId) {
		List<BoardAnswerDto> answers = boardService.getAnswersByQuestionId(questionId);
		return ResponseEntity.ok(answers);
	}

	// 답변 등록
	@PreAuthorize("hasRole('ROLE_admin')") // 관리자만 작성 가능
	@PostMapping("/questions/{questionId}/answers")
	public ResponseEntity<String> createAnswer(@PathVariable
	int questionId, @RequestBody
	BoardAnswerDto answer) {
		try {
			answer.setQuestionId(questionId);
			boardService.createAnswer(answer);
			return ResponseEntity.status(HttpStatus.CREATED).body("답변이 등록되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("답변 등록 중 오류가 발생했습니다.");
		}
	}

	// 답변 수정
	@PreAuthorize("hasRole('ROLE_admin')")
	@PutMapping("/answers/{id}")
	public ResponseEntity<String> updateAnswer(@PathVariable
	int id, @RequestBody
	BoardAnswerDto answer) {
		try {
			answer.setId(id);
			boardService.updateAnswer(answer);
			return ResponseEntity.ok("답변이 수정되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("답변 수정 중 오류가 발생했습니다.");
		}
	}

	// 답변 삭제
	@PreAuthorize("hasRole('ROLE_admin')")
	@DeleteMapping("/answers/{id}")
	public ResponseEntity<String> deleteAnswer(@PathVariable
	int id) {
		try {
			boardService.deleteAnswer(id);
			return ResponseEntity.ok("답변이 삭제되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("답변 삭제 중 오류가 발생했습니다.");
		}
	}
}
