package com.sateraito.mitap.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePasswordRequest {
	
	private String username;
	private String email;
	private String phoneNumber;
	private String newPassword;
	private String oldPassword;


}
