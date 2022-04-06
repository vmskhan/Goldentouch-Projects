package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Entity(name="zoom")
public class Zoom {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	 private String sdkKey;
	    private String signature;
	    private String meetingNumber;
	    private String password ;
	    private String userName;
	    private int testId;
	    
	    
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getTestId() {
			return testId;
		}
		public void setTestId(int testId) {
			this.testId = testId;
		}
		public String getSdkKey() {
			return sdkKey;
		}
		public void setSdkKey(String sdkKey) {
			this.sdkKey = sdkKey;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getMeetingNumber() {
			return meetingNumber;
		}
		public void setMeetingNumber(String meetingNumber) {
			this.meetingNumber = meetingNumber;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		


}
