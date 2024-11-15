package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardAnswerDto;
import com.ssafy.board.model.BoardQuestionDto;
import com.ssafy.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardQuestionDto> getAllQuestions() {
		return boardMapper.getAllQuestions();
	}

	@Override
	public BoardQuestionDto getQuestionById(int id) {
		return boardMapper.getQuestionById(id);
	}

	@Override
	public void createQuestion(BoardQuestionDto question) {
		boardMapper.createQuestion(question);
	}

	@Override
	public void updateQuestion(BoardQuestionDto question) {
		boardMapper.updateQuestion(question);
	}

	@Override
	public void deleteQuestion(int id) {
		boardMapper.deleteQuestion(id);
	}

	@Override
	public List<BoardAnswerDto> getAnswersByQuestionId(int questionId) {
		return boardMapper.getAnswersByQuestionId(questionId);
	}

	@Override
	public void createAnswer(BoardAnswerDto answer) {
		boardMapper.createAnswer(answer);
	}

	@Override
	public void updateAnswer(BoardAnswerDto answer) {
		boardMapper.updateAnswer(answer);
	}

	@Override
	public void deleteAnswer(int id) {
		boardMapper.deleteAnswer(id);
	}
}
