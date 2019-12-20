package com.sateraito.mitap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sateraito.mitap.converter.BooleanConvertor;
import com.sateraito.mitap.converter.StateTravelConvertor;
import com.sateraito.mitap.utils.EStateTravel;

@Entity(name = "travel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Travel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String unique_id;
	private int japanese_profile_required;
	private String list_place;
	private Date time_start;
	private Date time_finish;
	private String transport;
	private Date create_date;
	private Date update_date;
	private String location;
	private String note;
	private String schedule;
	private long id_user;
	
	@Convert(converter = StateTravelConvertor.class)
	private EStateTravel state;
	@Convert(converter = BooleanConvertor.class)
	private boolean public_flag;
	@Convert(converter = BooleanConvertor.class)
	private boolean del_flag;

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

	public int getJapanese_profile_required() {
		return japanese_profile_required;
	}

	public void setJapanese_profile_required(int japanese_profile_required) {
		this.japanese_profile_required = japanese_profile_required;
	}

	public String getList_place() {
		return list_place;
	}

	public void setList_place(String list_place) {
		this.list_place = list_place;
	}

	public Date getTime_start() {
		return time_start;
	}

	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}

	public Date getTime_finish() {
		return time_finish;
	}

	public void setTime_finish(Date time_finish) {
		this.time_finish = time_finish;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
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

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public EStateTravel getState() {
		return state;
	}

	public void setState(EStateTravel state) {
		this.state = state;
	}

}
