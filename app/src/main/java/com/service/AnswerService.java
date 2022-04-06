package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entities.Answer;

public interface AnswerService {
	Answer findById(int optid);
	List<Answer> findAllAnswer();
	List<Answer> findAllAnswerByQid(int qid);
}
