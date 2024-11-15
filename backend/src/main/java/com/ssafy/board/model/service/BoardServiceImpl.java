package com.ssafy.board.model.service;

import java.time.LocalDateTime;
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
		boardMapper.updateViewCount(id);
		return boardMapper.getQuestionById(id);
	}

	@Override
	public void createQuestion(BoardQuestionDto question) {
		question.setCreatedAt(LocalDateTime.now());
		question.setUpdatedAt(LocalDateTime.now());
		boardMapper.createQuestion(question);
	}

	@Override
	public void updateQuestion(BoardQuestionDto question) {
		question.setUpdatedAt(LocalDateTime.now());
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
		answer.setCreatedAt(LocalDateTime.now());
		answer.setUpdatedAt(LocalDateTime.now());
		boardMapper.createAnswer(answer);
	}

	@Override
	public void updateAnswer(BoardAnswerDto answer) {
		answer.setUpdatedAt(LocalDateTime.now());
		boardMapper.updateAnswer(answer);
	}

	@Override
	public void deleteAnswer(int id) {
		boardMapper.deleteAnswer(id);
	}
}
