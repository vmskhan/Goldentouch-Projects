package com.service;

import java.util.List;

import com.entities.Question;
import com.entities.Test;

public interface QuestionService {
	Question findById(int qid);
   
	void saveQuestion(Question question);
     
    void updateQuestion(Question question);
     
    void deleteQuestionById(int qid);
 
    List<Question> findAllTests(); 
    
    List<Question> findQuestionsByTid(int tid);
    
    Question findQuestionByTidAndIdx(int tid,int idx);
}
