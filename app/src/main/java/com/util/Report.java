package com.util;

import java.util.List;

import com.entities.Participation;
import com.entities.Question;
import com.entities.Submission;
import com.entities.Test;

public class Report {
	private Submission submission;
	private Question question;
	public final Submission getSubmission() {
		return submission;
	}
	public final void setSubmission(Submission submission) {
		this.submission = submission;
	}
	public final Question getQuestion() {
		return question;
	}
	public final void setQuestion(Question question) {
		this.question = question;
	}
	public Report(Submission submission, Question question) {
		super();
		this.submission = submission;
		this.question = question;
	}
	public Report() {
		
	}
	
	
}
