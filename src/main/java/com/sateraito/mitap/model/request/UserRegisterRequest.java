package com.sateraito.mitap.model.request;

public class UserRegisterRequest {
	private String username;
	private String email;
	private boolean accuracy_email;
	private String phone_number;
	private boolean accuracy_phone_number;
	private int type;
	private String avata;
	private String first_name;
	private String last_name;
	private boolean enable;
	private int gender;
	private String password;
	private String domain;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAccuracy_email() {
		return accuracy_email;
	}

	public void setAccuracy_email(boolean accuracy_email) {
		this.accuracy_email = accuracy_email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public boolean isAccuracy_phone_number() {
		return accuracy_phone_number;
	}

	public void setAccuracy_phone_number(boolean accuracy_phone_number) {
		this.accuracy_phone_number = accuracy_phone_number;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAvata() {
		return avata;
	}

	public void setAvata(String avata) {
		this.avata = avata;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
