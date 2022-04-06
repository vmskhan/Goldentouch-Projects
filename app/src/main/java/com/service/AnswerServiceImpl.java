package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AnswerDAO;
import com.entities.Answer;

@Service("answerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDAO dao;
	
	@Override
	public Answer findById(int optid) {
		System.out.println("answerservice-findbyid method called");
		return dao.findById(optid);
	}

	@Override
	public List<Answer> findAllAnswer() {
		System.out.println("answerservice-findallanswers method called");
		return dao.findAllAnswers();
	}

	@Override
	public List<Answer> findAllAnswerByQid(int qid) {
		System.out.println("answerservice-findallanswersbyqid method called");
		return dao.findAllAnswersByQid(qid);
	}

}
