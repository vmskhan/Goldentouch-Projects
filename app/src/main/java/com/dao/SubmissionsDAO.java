package com.dao;

import java.util.List;

import com.entities.SubmissionPk;
import com.entities.Submission;

public interface SubmissionsDAO {
	Submission findById(SubmissionPk pk);
	 
    void saveSubmission(Submission submission);
     
    void deleteSubmission(Submission submission);
     
    List<Submission> findAllSubmissionss();
    List<Submission> findSubmissionsById(SubmissionPk pk);

	List<Submission> findSubmissionsByTid(int id);

	List<Submission> findSubmissionsByTidAndUid(int tid, int uid);
}
