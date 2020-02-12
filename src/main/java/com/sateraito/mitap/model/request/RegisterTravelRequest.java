package com.sateraito.mitap.model.request;

public class RegisterTravelRequest {
	private int japanese_profile_required;
	private String placeToVisit;
	private String time_start;
	private String time_finish;
	private String transport;
	private String destination;
	private String note;
	private String schedule;
	public int getJapanese_profile_required() {
		return japanese_profile_required;
	}
	public void setJapanese_profile_required(int japanese_profile_required) {
		this.japanese_profile_required = japanese_profile_required;
	}
	public String getPlaceToVisit() {
		return placeToVisit;
	}
	public void setPlaceToVisit(String placeToVisit) {
		this.placeToVisit = placeToVisit;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_finish() {
		return time_finish;
	}
	public void setTime_finish(String time_finish) {
		this.time_finish = time_finish;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	

}
