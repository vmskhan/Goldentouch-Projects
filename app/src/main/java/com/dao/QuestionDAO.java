package com.dao;

import java.util.List;

import com.entities.Question;

public interface QuestionDAO {
	Question findById(int qid);
	 
    void saveQuestion(Question question);
     
    void deleteQuestionById(int qid);
     
    List<Question> findAllQuestions();
    
    List<Question> findAllQuestionsByTid(int tid);
 
    Question findQuestionByTidAndIdx(int tid,int idx);
}
