package com.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class SubmissionPk implements Serializable{
	
	private int uid;
	private int tid;
	private int qid;
	public  int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	

}
