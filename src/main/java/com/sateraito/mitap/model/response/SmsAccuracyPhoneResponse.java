package com.sateraito.mitap.model.response;

import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.entity.SmsAccuracyPhone;
import com.sateraito.mitap.utils.ESMSTypeAccuracyPhone;

public class SmsAccuracyPhoneResponse {
	private long id;

	private String unique_id;
	private String status;
	private String error;
	private String body_message;
	private String code;
	private String price;
	private String price_unit;
	private long user_id;
	private String create_date;
	private String update_date;
	private ESMSTypeAccuracyPhone type;
	
	public SmsAccuracyPhoneResponse(){
		super();
	}
	
	public SmsAccuracyPhoneResponse(SmsAccuracyPhone smsAccuracy){
		super();
		setId(smsAccuracy.getId());
		setUnique_id(smsAccuracy.getUnique_id());
		setStatus(smsAccuracy.getStatus());
		setError(smsAccuracy.getError());
		setBody_message(smsAccuracy.getBody_message());
		setCode(smsAccuracy.getCode());
		setPrice(smsAccuracy.getPrice());
		setPrice_unit(smsAccuracy.getPrice_unit());
		setUser_id(smsAccuracy.getUser_id());
		setCreate_date(Constants.fomat_1.format(smsAccuracy.getCreate_date()));
		setUpdate_date(Constants.fomat_1.format(smsAccuracy.getUpdate_date()));
		setType(smsAccuracy.getType());
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

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
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

	public ESMSTypeAccuracyPhone getType() {
		return type;
	}

	public void setType(ESMSTypeAccuracyPhone type) {
		this.type = type;
	}

	
	
	
}
