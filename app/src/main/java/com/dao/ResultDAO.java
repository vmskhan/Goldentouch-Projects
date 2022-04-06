package com.dao;

import java.util.List;

import com.entities.Result;

public interface ResultDAO {
	Result findById(int tid);
	 
    void saveResult(Result result);
     
    void deleteResultById(int tid);
     
    List<Result> findAllResults();
}
