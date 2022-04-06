package com.service;

import java.util.List;

import com.entities.Test;

public interface TestService {
	Test findById(int tid);
    
    void saveTest(Test test);
     
    void updateTest(Test test);
     
    void deleteTestById(int tid);
 
    List<Test> findAllTests(); 
     
    Test findTestByTestName(String tname);
    
    List<Test> findByState(String state);

	List<Test> findAllTestsByUid(int uid);
	List<Test> findAllTestsByPid(int pid);
}
