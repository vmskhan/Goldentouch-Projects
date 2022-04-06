package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SubmissionsDAO;
import com.entities.Participation;
import com.entities.Submission;
import com.entities.SubmissionPk;

@Service("submissionService")
@Transactional
public class SubmissionServiceImpl implements SubmissionService {

	@Autowired
	private SubmissionsDAO dao;
	
	@Override
	public Submission findById(SubmissionPk pk) {
		System.out.println("submissionservice-findbyid method called");
		return dao.findById(pk);
	}

	@Override
	public void saveSubmission(Submission submission) {
		System.out.println("submissionservice-savesubmission method called");
		dao.saveSubmission(submission);
		
	}

	@Override
	public void deleteSubmission(Submission submission) {
		System.out.println("submissionservice-deletesubmission method called");
		dao.deleteSubmission(submission);
	}

	@Override
	public List<Submission> findAllSubmissions() {
		System.out.println("submissionservice-findallsubissions method called");
		return dao.findAllSubmissionss();
	}

	@Override
	public List<Submission> findSubmissionsById(SubmissionPk pk) {
		System.out.println("submissionservice-findsubissionbyid method called");
		return dao.findSubmissionsById(pk);
	}

	@Override
	public List<Submission> findSubmissionByTid(int id) {
		System.out.println("submissionservice-findsubmissionbytid method called");
		return dao.findSubmissionsByTid(id);
	}

	@Override
	public List<Submission> findSubmissionByTidAndUid(int tid, int uid) {
		System.out.println("submissionservice-findsubmissionbytidanduid method called");
		return dao.findSubmissionsByTidAndUid(tid,uid);
	}
	
	@Override
	public void updateSubmission(Submission s) {
		System.out.println("participationservice-updateparticipation method called");
		Submission entity = dao.findById(s.getCompkey());
		if(entity != null) {
			entity.setChoice(s.getChoice());
			entity.setMark(s.getMark());
			entity.setRightanswer(s.getRightanswer());
			entity.setState(s.getState());
		}
	}

}
