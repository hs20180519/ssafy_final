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

	void updateViewCount(int id);

	void createQuestion(BoardQuestionDto question);

	void updateQuestion(BoardQuestionDto question);

	void deleteQuestion(int id);

	// 답변 관련 메서드
	List<BoardAnswerDto> getAnswersByQuestionId(int questionId);

	void createAnswer(BoardAnswerDto answer);

	void updateAnswer(BoardAnswerDto answer);

	void deleteAnswer(int id);
}
