package com.service;

import java.util.List;

import com.entities.Submission;
import com.entities.SubmissionPk;

public interface SubmissionService {
	Submission findById(SubmissionPk pk);
	void saveSubmission(Submission submission);
	void deleteSubmission(Submission submission);
	List<Submission> findAllSubmissions();
	List<Submission> findSubmissionsById(SubmissionPk pk);
	List<Submission> findSubmissionByTid(int id);
	List<Submission> findSubmissionByTidAndUid(int tid,int uid);
	void updateSubmission(Submission s);
}
