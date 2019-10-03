package com.sateraito.mitap.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePasswordByTokenRequest {
	private String newPassword;
	private String oldPassword;


}
