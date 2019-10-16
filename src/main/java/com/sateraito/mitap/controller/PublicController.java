package com.sateraito.mitap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PublicController extends MitapController {
	@RequestMapping(value = "css/hello", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<String> index(HttpServletRequest request) {
		return new ResponseEntity<String>("hoakngien", HttpStatus.OK);
	}  
	
}
