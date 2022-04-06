package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Answer;
import com.entities.Result;

@Repository("ResultDAO")
@Transactional
public class ResultDAOImpl extends AbstractDAO<Integer, Result> implements ResultDAO{

	@Override
	public Result findById(int tid) {
		System.out.println("result-findbyid method called");
		return getByKey(tid);
	}

	@Override
	public void saveResult(Result result) {
		System.out.println("result-saveresult method called");
		persist(result);
		
	}

	@Override
	public void deleteResultById(int tid) {
		System.out.println("result-deleteresultbyid method called");
		Criteria criteria =  createEntityCriteria();
	    Result result=(Result)criteria.add(Restrictions.eq("tid", tid)).uniqueResult();
	    delete(result);	
	}

	@Override
	public List<Result> findAllResults() {
		System.out.println("result-findallresults method called");
		Criteria criteria = createEntityCriteria();
        return (List<Result>) criteria.list();
	}

}
