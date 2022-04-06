package com.dao;

import java.util.List;

import com.entities.Answer;

public interface AnswerDAO {
	Answer findById(int optid);
	 
    void saveAnswer(Answer answer);
     
    void deleteAnswerById(int optid);
     
    List<Answer> findAllAnswers();
    
    List<Answer> findAllAnswersByQid(int qid);
}
