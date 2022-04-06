package com.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="temp_store")
public class temp {
	@Id
	int uid;
	int tempval;
	
	public int getTempval() {
		return tempval;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setTempval(int tempval) {
		this.tempval = tempval;
	}

	
}
