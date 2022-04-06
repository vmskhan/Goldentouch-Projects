package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Result;
import com.entities.Submission;
import com.entities.SubmissionPk;

@Repository("SubmissionsDAO")
@Transactional
public class SubmissionsDAOImpl extends AbstractDAO<SubmissionPk, Submission> implements SubmissionsDAO{

	@Override
	public Submission findById(SubmissionPk pk) {
		System.out.println("submission-findbyid method called");
		return getByKey(pk);
	}

	@Override
	public void saveSubmission(Submission submissions) {
		System.out.println("submission-savesubmission method called");
		persist(submissions);
		
	}

	@Override
	public void deleteSubmission(Submission submission) {
		System.out.println("submission-deletesubmission method called");
	    delete(submission);
	}

	@Override
	public List<Submission> findAllSubmissionss() {
		System.out.println("submission-findallsubmissions method called");
		Criteria criteria = createEntityCriteria();
        return (List<Submission>) criteria.list();
	}

	@Override
	public List<Submission> findSubmissionsById(SubmissionPk pk) {
		System.out.println("submission-findsubmissionsbyid method called");
		Criteria criteria = createEntityCriteria();
        return (List<Submission>) getByKey(pk);
	}

	@Override
	public List<Submission> findSubmissionsByTid(int id) {
		System.out.println("submission-findsubmissionsbytid method called");
		Criteria criteria = createEntityCriteria();
        return (List<Submission>) criteria.add(Restrictions.eq("compkey.tid",id)).list();
	}

	@Override
	public List<Submission> findSubmissionsByTidAndUid(int tid, int uid) {
		System.out.println("submission-findsubmissionsbytid method called");
		Criteria criteria = createEntityCriteria();
        return (List<Submission>) criteria.add(Restrictions.eq("compkey.tid",tid)).add(Restrictions.eq("compkey.uid",uid)).list();
	}

}
