package com.sateraito.mitap.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.sateraito.mitap.service.TravelService;
import com.sateraito.mitap.service.UserDetailsServiceImpl;

public class MitapController {
	@Autowired
	protected UserDetailsServiceImpl userService;
	@Autowired
	protected TravelService travelService;

}
