package com.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Question;
import com.service.TestService;

@Repository("QuestionDAO")
@Transactional
public class QuestionDAOImpl extends AbstractDAO<Integer, Question> implements QuestionDAO{
	
	@Autowired
	TestService ts;
	
	@Override
	public Question findById(int qid) {
		System.out.println("question-findbyid method called");
		return getByKey(qid);
	}

	@Override
	public void saveQuestion(Question question) {
		System.out.println("question-savequestion method called");
		persist(question);		
	}

	@Override
	public void deleteQuestionById(int qid) {
		System.out.println("question-deletequestionbyid method called");
		Criteria criteria =  createEntityCriteria();
	    Question question=(Question)criteria.add(Restrictions.eq("qid", qid)).uniqueResult();
	        delete(question);
	}

	@Override
	public List<Question> findAllQuestions() {
		System.out.println("question-findallquestions method called");
		Criteria criteria = createEntityCriteria();
        return (List<Question>) criteria.list();
	}

	
	@Override
	public List<Question> findAllQuestionsByTid(int tid) {
		System.out.println("question-fidallquestionsbytid method called");
		Criteria criteria = createEntityCriteria();
		List<Question> qa=(List<Question>) criteria.add(Restrictions.eq("test.tid", tid)).list();
		List<Question> ql=new LinkedList<Question>();
		Question q;
		for(Question qp:qa)
		{
			System.out.println("question images--------------------"+qp.getQuestionImage());
		}
		return qa;
	}

	@Override
	public Question findQuestionByTidAndIdx(int tid, int idx) {
		System.out.println("question-findallqustionbytidandidx method called");
		Criteria criteria =  createEntityCriteria();
		Map<String,Integer> mp = new HashMap<>();
		mp.put("test.tid", tid);
		mp.put("idx", idx);
	    Question question=(Question)criteria.add(Restrictions.allEq(mp)).uniqueResult();
	    return question;
	}

}
