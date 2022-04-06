package com.zoomComponent;

public class Meeting {
 
	private String topic;
	 private int type;
	 private boolean pre_schedule;
	 //private String start_time;
	 //private int duration;
	 private String schedule_for;
	 //private String timezone;
	 private String password;
	 private boolean default_password;
	 private String agenda;
	 private ZoomMeetingTrackingFieldsDTO tracking;
	 private ZoomMeetingSettingsDTO settings;
	
	 public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isPre_schedule() {
		return pre_schedule;
	}
	public void setPre_schedule(boolean pre_schedule) {
		this.pre_schedule = pre_schedule;
	}
	public String getSchedule_for() {
		return schedule_for;
	}
	public void setSchedule_for(String schedule_for) {
		this.schedule_for = schedule_for;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDefault_password() {
		return default_password;
	}
	public void setDefault_password(boolean default_password) {
		this.default_password = default_password;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public ZoomMeetingTrackingFieldsDTO getTracking() {
		return tracking;
	}
	public void setTracking(ZoomMeetingTrackingFieldsDTO tracking) {
		this.tracking = tracking;
	}
	public ZoomMeetingSettingsDTO getSettings() {
		return settings;
	}
	public void setSettings(ZoomMeetingSettingsDTO settings) {
		this.settings = settings;
	}
	 	 
}
