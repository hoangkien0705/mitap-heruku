package com.sateraito.mitap.model.response;

import java.util.List;

import com.sateraito.mitap.entity.MitapUser;

public class UserInfoResponse extends UserResponse { 
	
	public UserInfoResponse(MitapUser user) {
		super(user);
	}

	private List<String> roles;
	private UserDirectionResponse userDirection;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public UserDirectionResponse getUserDirection() {
		return userDirection;
	}

	public void setUserDirection(UserDirectionResponse userDirection) {
		this.userDirection = userDirection;
	}
	
	
}
