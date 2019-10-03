package com.sateraito.mitap.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterTravelRequest {
	private String japanese_profile_required;
	private String list_place;
	private String time_start;
	private String time_finish;
	private String transport;
	private String location;
	private String note;
	private String schedule;

}
