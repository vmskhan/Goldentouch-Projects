package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ResultDAO;
import com.entities.Result;

@Service("resultService")
@Transactional
public class ResultServiceImpl implements ResultService{
	
	@Autowired
	ResultDAO dao;
	@Override
	public Result findById(int tid) {
		System.out.println("resultservice-findbyid method called");
		return dao.findById(tid);
	}

	@Override
	public void saveResult(Result result) {
		System.out.println("resultservice-saveresult method called");
		dao.saveResult(result);
	}

	@Override
	public void updateResult(Result result) {
		System.out.println("resultservice-updateresult method called");
	//	Result entity=dao.findById(result.ge)
		
	}

	@Override
	public void deleteResultById(int tid) {
		System.out.println("resultservice-deleteresultbyid method called");
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Result> findAllResults() {
		System.out.println("resultservice-findallresults method called");
		// TODO Auto-generated method stub
		return null;
	}

}
