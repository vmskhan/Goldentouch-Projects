package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Answer;
import com.entities.Question;

@Repository("AnswerDAO")
public class AnswerDAOImpl extends AbstractDAO<Integer, Answer> implements AnswerDAO{

	@Override
	public Answer findById(int optid) {
		System.out.println("answer-findbyid method called");
		return getByKey(optid);
	}

	@Override
	public void saveAnswer(Answer answer) {
		System.out.println("answer-saveanswer method called");
		persist(answer);
		
	}

	@Override
	public void deleteAnswerById(int optid) {
		System.out.println("deleteanswerbyid method called");
		Criteria criteria =  createEntityCriteria();
	       Answer answer=(Answer)criteria.add(Restrictions.eq("optid", optid)).uniqueResult();
	        delete(answer);
		
	}

	@Override
	public List<Answer> findAllAnswers() {
		System.out.println("finallanswers method called");
		Criteria criteria = createEntityCriteria();
        return (List<Answer>) criteria.list();
	}

	@Override
	public List<Answer> findAllAnswersByQid(int qid) {
		System.out.println("answer-findallanswersbyqid method called");
		Criteria criteria = createEntityCriteria();
		return (List<Answer>) criteria.add(Restrictions.eq("question.qid", qid)).list();
	}

}
