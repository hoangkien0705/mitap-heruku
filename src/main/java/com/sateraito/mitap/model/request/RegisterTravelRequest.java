package com.sateraito.mitap.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterTravelRequest {
	private int japanese_profile_required;
	private String placeToVisit;
	private String time_start;
	private String time_finish;
	private String transport;
	private String destination;
	private String note;
	private String schedule;

}
