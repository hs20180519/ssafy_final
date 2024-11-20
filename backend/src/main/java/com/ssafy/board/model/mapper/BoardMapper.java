package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardAnswerDto;
import com.ssafy.board.model.BoardQuestionDto;

@Mapper
public interface BoardMapper {
	// 질문 관련 메서드
	List<BoardQuestionDto> getAllQuestions();

	BoardQuestionDto getQuestionById(int id);

<<<<<<< HEAD
<<<<<<< HEAD
	void updateViewCount(int id);

=======
>>>>>>> f76ab5d (:sparkles: Feat: 질문/답변 CRUD)
=======
	void updateViewCount(int id);

>>>>>>> 6745995 (:sparkles: Feat: 조회수 증가)
	void createQuestion(BoardQuestionDto question);

	void updateQuestion(BoardQuestionDto question);

	void deleteQuestion(int id);

	// 답변 관련 메서드
	List<BoardAnswerDto> getAnswersByQuestionId(int questionId);

	void createAnswer(BoardAnswerDto answer);

	void updateAnswer(BoardAnswerDto answer);

	void deleteAnswer(int id);
}
