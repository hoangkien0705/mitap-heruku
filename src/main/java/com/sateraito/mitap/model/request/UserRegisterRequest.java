package com.sateraito.mitap.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
	
}
