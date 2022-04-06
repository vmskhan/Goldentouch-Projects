package com.util;

import com.entities.Participation;
import com.entities.Test;

public class TestPart {
	private Test test;
	private Participation participation;
	public TestPart(Test t, Participation p) {
		this.test = t;
		this.participation = p;
	}
	public TestPart(Test t) {
		this.test = t;
	}
	public Participation getParticipation() {
		return participation;
	}
	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
}
