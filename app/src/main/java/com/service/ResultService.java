package com.service;

import java.util.List;

import com.entities.Result;

public interface ResultService {
	Result findById(int tid);
    
    void saveResult(Result result);
     
    void updateResult(Result result);
     
    void deleteResultById(int tid);
 
    List<Result> findAllResults(); 
     
}
