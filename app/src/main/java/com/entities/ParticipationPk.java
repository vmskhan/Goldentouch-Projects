package com.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ParticipationPk implements Serializable {
	private int uid;
	private int tid;
	public int getUid() {
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

}
