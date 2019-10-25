package com.sateraito.mitap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sateraito.mitap.converter.TypeSMSAccuracyConvertor;
import com.sateraito.mitap.utils.ESMSTypeAccuracyPhone;

@Entity(name = "sms_accuracy_phone")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SmsAccuracyPhone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String unique_id;
	private String status;
	private String error;
	private String body_message;
	private String code;
	private Date time_auth;
	private String price;
	private String price_unit;
	private long user_id;
	private Date create_date;
	private Date update_date;
	
	@Convert(converter = TypeSMSAccuracyConvertor.class)
	private ESMSTypeAccuracyPhone type;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getBody_message() {
		return body_message;
	}

	public void setBody_message(String body_message) {
		this.body_message = body_message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice_unit() {
		return price_unit;
	}

	public void setPrice_unit(String price_unit) {
		this.price_unit = price_unit;
	}

	public Date getTime_auth() {
		return time_auth;
	}

	public void setTime_auth(Date time_auth) {
		this.time_auth = time_auth;
	}

	public ESMSTypeAccuracyPhone getType() {
		return type;
	}

	public void setType(ESMSTypeAccuracyPhone type) {
		this.type = type;
	}

	
	
	
	
}
