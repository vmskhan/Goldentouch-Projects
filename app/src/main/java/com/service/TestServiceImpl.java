package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TestDAO;
import com.entities.Test;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService{
	@Autowired
	TestDAO dao;
	@Override
	public Test findById(int tid) {
		System.out.println("testservice-findbyid method called");
		return dao.findById(tid);
	}

	@Override
	public void saveTest(Test test) {
		System.out.println("testservice-savetest method called");
		dao.saveTest(test);
		
	}

	@Override
	public void updateTest(Test test) {
		System.out.println("testservice-updatetest method called");
		Test entity=dao.findById(test.getTid());
		if(entity!=null) {
			entity.setDate(test.getDate());
			entity.setDuration(test.getDuration());
			entity.setStart_time(test.getStart_time());
			entity.setTname(test.getTname());
			entity.setState(test.getState());
			entity.setTotalMarks(test.getTotalMarks());
			entity.setNeedPayment(test.isNeedPayment());
			entity.setAmount(test.getAmount());
		}
		
	}

	@Override
	public void deleteTestById(int tid) {
		System.out.println("testservice-dletetestbyid method called");
		dao.deleteTestById(tid);
		
	}

	@Override
	public List<Test> findAllTests() {
		System.out.println("testservice-findalltests method called");
		return dao.findAllTests();
	}

	@Override
	public Test findTestByTestName(String tname) {
		System.out.println("testservice-findtestbytestname method called");
		return dao.findTestByTestName(tname);
	}

	@Override
	public List<Test> findByState(String state) {
		System.out.println("testservice-findbystate method called");
		return dao.findByState(state);
	}

	@Override
	public List<Test> findAllTestsByUid(int uid) {
		// TODO Auto-generated method stub
		return dao.findAllTestsByUid(uid);
	}

	@Override
	public List<Test> findAllTestsByPid(int pid) {
		// TODO Auto-generated method stub
		return dao.findAllTestsByPid(pid);
	}

}
