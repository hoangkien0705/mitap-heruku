package com.sateraito.mitap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sateraito.mitap.converter.StateDirectTravelConvertor;
import com.sateraito.mitap.utils.EStateDirectTravel;


@Entity(name = "direct_travel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DirectTravel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String unique_id;
	private long id_user;
	private long id_direct_user;
	private Date update_date;
	private int del_flag;

	@Convert(converter = StateDirectTravelConvertor.class)
	private EStateDirectTravel state;

	private Date update_time;
	private String message;
	private Date create_date;
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
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	public long getId_direct_user() {
		return id_direct_user;
	}
	public void setId_direct_user(long id_direct_user) {
		this.id_direct_user = id_direct_user;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public int getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}
	public EStateDirectTravel getState() {
		return state;
	}
	public void setState(EStateDirectTravel state) {
		this.state = state;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
	

}
