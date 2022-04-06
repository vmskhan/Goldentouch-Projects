package com.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	private String name;
	private String password;
	private String emailid;
	private boolean emailVerified=false;
	private String claim="user";
	private int last_attempted;  
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Test> test;
	
	
	public List<Test> getTest() {
		return test;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

	public User(String name, String password, String emailid) {
		super();
		this.name = name;
		this.password = password;
		this.emailid = emailid;
	}
	
	public User() {
		
	}
	
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", password=" + password + ", emailid=" + emailid
				+ ", emailVerified=" + emailVerified + ", claim=" + claim + "]";
	}
	public int getLast_attempted() {
		return last_attempted;
	}
	public void setLast_attempted(int last_attempted) {
		this.last_attempted = last_attempted;
	}
	
}