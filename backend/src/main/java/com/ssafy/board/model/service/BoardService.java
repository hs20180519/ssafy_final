package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.BoardAnswerDto;
import com.ssafy.board.model.BoardQuestionDto;

public interface BoardService {
	List<BoardQuestionDto> getAllQuestions();

	BoardQuestionDto getQuestionById(int id);

	void createQuestion(BoardQuestionDto question);

	void updateQuestion(BoardQuestionDto question);

	void deleteQuestion(int id);

	List<BoardAnswerDto> getAnswersByQuestionId(int questionId);

	void createAnswer(BoardAnswerDto answer);

	void updateAnswer(BoardAnswerDto answer);

	void deleteAnswer(int id);
}
