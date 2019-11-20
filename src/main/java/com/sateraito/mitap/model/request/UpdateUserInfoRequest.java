package com.sateraito.mitap.model.request;

public class UpdateUserInfoRequest {
	private String first_name;// : userInfo.first_name,
	private String last_name;// userInfo.last_name,
	private String email;// userInfo.email,
	private String address;// userInfo.address,
	private String national;// userInfo.national,
	private String birday;// userInfo.birday,

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getBirday() {
		return birday;
	}

	public void setBirday(String birday) {
		this.birday = birday;
	}

}
