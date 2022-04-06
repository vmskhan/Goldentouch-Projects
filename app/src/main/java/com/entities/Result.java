package com.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Result{
	@EmbeddedId
	private CompKey_Result compkey;

	private int total;

	public CompKey_Result getCompkey() {
		return compkey;
	}

	public void setCompkey(CompKey_Result compkey) {
		this.compkey = compkey;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	} 
}
