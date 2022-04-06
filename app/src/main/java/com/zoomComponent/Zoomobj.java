package com.zoomComponent;
import java.io.Serializable;
import java.util.List;

public class Zoomobj implements Serializable {

    private static final long serialVersionUID = 1L;

//    private Long id;
//
//    private String uuid;
//
//    private String assistant_id;
//
//    private String host_email;
//
//    private String registration_url;
//
//    private String topic;
//
//    private Integer type;
//
//    private String start_time;
//
//    private Integer duration;
//
//    private String schedule_for;
//
//    private String timezone;
//
//    private String created_at;
//
//    private String password;
//
//    private String agenda;
//
//    private String start_url;
//
//    private String join_url;
//
//    private String h323_password;
//
//    private Integer pmi;

//    private ZoomMeetingRecurrenceDTO recurrence;
//
   private List<ZoomMeetingTrackingFieldsDTO> tracking_fields;
public List<ZoomMeetingTrackingFieldsDTO> getTracking_fields() {
	return tracking_fields;
}
public void setTracking_fields(List<ZoomMeetingTrackingFieldsDTO> tracking_fields) {
	this.tracking_fields = tracking_fields;
}
public ZoomMeetingSettingsDTO getSettings() {
	return settings;
}
public void setSettings(ZoomMeetingSettingsDTO settings) {
	this.settings = settings;
}
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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getDefault_password() {
	return default_password;
}
public void setDefault_password(String default_password) {
	this.default_password = default_password;
}
public String getAgenda() {
	return agenda;
}
public void setAgenda(String agenda) {
	this.agenda = agenda;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
//
//    private List<ZoomMeetingOccurenceDTO> occurrences;
//
   private ZoomMeetingSettingsDTO settings;

    private String topic;
    private int type;
    private String password;
    private String default_password;
    private String agenda;
    
//    private boolean pre_schedule=false;
//    private int duration;
    
   
}