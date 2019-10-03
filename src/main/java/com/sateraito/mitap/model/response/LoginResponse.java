package com.sateraito.mitap.model.response;

public class LoginResponse {
	private String token;
	private UserInfoResponse userInfo;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserInfoResponse getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoResponse userInfo) {
		this.userInfo = userInfo;
	}

}
