package com.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Participation;
import com.entities.ParticipationPk;
import com.entities.Question;
import com.entities.Submission;
import com.entities.SubmissionPk;
import com.entities.Test;
import com.service.ParticipationService;
import com.service.QuestionService;
import com.service.SubmissionService;
import com.service.TestService;

@Component
public class ReportHelper {
	
	/*@Autowired
	private static TestService testService;
	
	@Autowired
	private static ParticipationService participationService;*/
	
	
	
	@Autowired
	private  SubmissionService submissionService;
	

	
	@Autowired
	private  QuestionService questionService;
	
	public  List<Report> getReport(int tid,int uid) {
		System.out.println("reporthelper-getreport method called");
		SubmissionPk sk=new SubmissionPk();
		List<Report> reports=new ArrayList<Report>();
		System.out.println("Got here........");
		List<Question> questions=questionService.findQuestionsByTid(tid);
		System.out.println("After service.....");
		Iterator<Question> itr=questions.iterator();
		while(itr.hasNext()) {
			Question temp=itr.next();
			sk.setTid(tid);
			sk.setUid(uid);
			sk.setQid(temp.getQid());
			Submission sub=submissionService.findById(sk);
			Report report=new Report(sub,temp);
			reports.add(report);
		}
		return reports;
	}

}
