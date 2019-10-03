package com.sateraito.mitap.model.response;

import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.entity.Travel;
import com.sateraito.mitap.utils.EStateTravel;

public class TravelResponse {
	private long id;
	private String unique_id;
	private String japanese_profile_required;
	private String list_place;
	private String time_start;
	private String time_finish;
	private String transport;
	private String create_date;
	private String update_date;
	private String location;
	private boolean public_flag;
	private boolean del_flag;
	private String note;
	private String schedule;
	private EStateTravel state;
	private long id_user;
	
	public TravelResponse(){}
	
	public TravelResponse(Travel travel) {
		setId(travel.getId());
		setUnique_id(travel.getUnique_id());
		setJapanese_profile_required(travel.getJapanese_profile_required());
		setList_place(travel.getList_place());
		setTime_start(Constants.fomat.format(travel.getTime_start()));
		setTime_finish(Constants.fomat.format(travel.getTime_finish()));
		setTransport(travel.getTransport());
		setCreate_date(Constants.fomat.format(travel.getCreate_date()));
		setUpdate_date(Constants.fomat.format(travel.getUpdate_date()));
		setLocation(travel.getLocation());
		setPublic_flag(travel.isPublic_flag());
		setDel_flag(travel.isDel_flag());
		setNote(travel.getNote());
		setSchedule(travel.getSchedule());
		setState(travel.getState());
		setId_user(travel.getId_user());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	public String getJapanese_profile_required() {
		return japanese_profile_required;
	}

	public void setJapanese_profile_required(String japanese_profile_required) {
		this.japanese_profile_required = japanese_profile_required;
	}

	public String getList_place() {
		return list_place;
	}

	public void setList_place(String list_place) {
		this.list_place = list_place;
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

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isPublic_flag() {
		return public_flag;
	}

	public void setPublic_flag(boolean public_flag) {
		this.public_flag = public_flag;
	}

	public boolean isDel_flag() {
		return del_flag;
	}

	public void setDel_flag(boolean del_flag) {
		this.del_flag = del_flag;
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

	public EStateTravel getState() {
		return state;
	}

	public void setState(EStateTravel state) {
		this.state = state;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

}
