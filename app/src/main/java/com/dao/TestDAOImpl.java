package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Test;
import com.entities.User;

@Repository("TestDAO")
@Transactional
public class TestDAOImpl extends AbstractDAO<Integer, Test> implements TestDAO{

	@Override
	public Test findById(int tid) {
		System.out.println("test-findbyid method called");
		return getByKey(tid);
	}

	@Override
	public void saveTest(Test test) {
		System.out.println("test-savetest method called");
		persist(test);
		
	}

	@Override
	public void deleteTestById(int tid) {
		System.out.println("test-deletetestbyid method called");
		Criteria criteria =  createEntityCriteria();
	       Test test=(Test)criteria.add(Restrictions.eq("tid", tid)).uniqueResult();
	        delete(test);
		
	}

	@Override
	public List<Test> findAllTests() {
		System.out.println("test-findalltests method called");
		Criteria criteria = createEntityCriteria();
        return (List<Test>) criteria.list();
	}

	@Override
	public Test findTestByTestName(String tname) {
		System.out.println("test-findtestbytestname method called");
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("tname", tname));
        
        return (Test) criteria.uniqueResult();
	}

	@Override
	public List<Test> findByState(String state) {
		System.out.println("test-findbystate method called");
		Criteria criteria = createEntityCriteria();
		return (List<Test>)criteria.add(Restrictions.eq("state", state)).list();
	}

	@Override
	public List<Test> findAllTestsByUid(int uid) {
		// TODO Auto-generated method stub
		Criteria criteria = createEntityCriteria();
		return (List<Test>)criteria.add(Restrictions.eq("user.uid", uid)).list();
	}

	@Override
	public List<Test> findAllTestsByPid(int pid) {
		// TODO Auto-generated method stub
		Criteria criteria = createEntityCriteria();
		return (List<Test>)criteria.add(Restrictions.eq("pid", pid)).list();
	}

}
