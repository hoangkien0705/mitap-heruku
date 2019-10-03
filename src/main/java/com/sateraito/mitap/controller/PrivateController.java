package com.sateraito.mitap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sateraito.mitap.constant.Constants;


@Controller
@RequestMapping(value = Constants.API_PRIVATE)
public class PrivateController {

	@RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<String> userInfo(HttpServletRequest request) {
		return new ResponseEntity<String>("hoakngien", HttpStatus.OK);
	}  
	
}
