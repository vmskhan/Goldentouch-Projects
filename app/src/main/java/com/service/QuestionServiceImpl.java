package com.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.QuestionDAO;
import com.entities.Question;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionDAO dao;

	@Override
	public Question findById(int qid) {
		System.out.println("questionservice-findbyid method called");
		return dao.findById(qid);
	}

	@Override
	public void saveQuestion(Question question) {
		System.out.println("questionservice-savequestion method called");
		dao.saveQuestion(question);
	}

	@Override
	public void updateQuestion(Question question) {
		System.out.println("questionservice-updatequestion method called");
		Question entity = dao.findById(question.getQid());
		if(entity != null) {
			entity.setAnswerImage(question.getAnswerImage());
			entity.setAnswerText(question.getAnswerText());
			entity.setMark(question.getMark());
			entity.setQuestionText(question.getQuestionText());
			MultipartFile m;
			//m = (MultipartFile) ImageIO.createImageInputStream(question.getQuestionImage());
			entity.setQuestionImage(question.getQuestionImage());
			
			entity.setIdx(question.getIdx());
		}
	}

	@Override
	public void deleteQuestionById(int qid) {
		System.out.println("questionservice-deletequestionbyid method called");
		dao.deleteQuestionById(qid);				
	}

	@Override
	public List<Question> findAllTests() {
		System.out.println("questionservice-findalltests method called");
		return dao.findAllQuestions();
	}

	@Override
	public List<Question> findQuestionsByTid(int tid) {
		System.out.println("questionservice-findquestionbytid method called");
		return dao.findAllQuestionsByTid(tid);
	}

	@Override
	public Question findQuestionByTidAndIdx(int tid, int idx) {
		System.out.println("questionservice-findquestionbytidandidx method called");
		return dao.findQuestionByTidAndIdx(tid, idx);
	}

}
