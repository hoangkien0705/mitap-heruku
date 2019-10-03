package com.sateraito.mitap.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {
	private String username;
	private String email;
	private String password;

}
