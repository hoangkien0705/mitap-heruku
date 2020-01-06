package com.sateraito.mitap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.model.request.UpdatePasswordByTokenRequest;
import com.sateraito.mitap.model.request.UpdatePasswordRequest;
import com.sateraito.mitap.model.request.UpdateUserInfoRequest;
import com.sateraito.mitap.model.request.UserAuthPhoneRequest;
import com.sateraito.mitap.model.request.UserRegisterRequest;
import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.utils.Log;


@Controller
public class UserController extends MitapController {
	
	@RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<ReponseMdl> index(HttpServletRequest request) {
		String uri = request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" + request.getServerPort() + // ":" + "8080"
	             request.getRequestURI() +       // "/people"
	            (request.getQueryString() != null ? "?" +
	             request.getQueryString() : "");
		Log.d("currenturi: " + uri);
		return userService.index(uri);
	}
	
	
	/**
	 * Đăng ký với số điện thoại (Đã thực hiện xác thực ở client qua account kit)
	 * @parram (email || username), phone_number, password, accuracy_phone_number = true
	 */
	@RequestMapping(value = Constants.REGISTER_ACCURACY_PHONE, method = { RequestMethod.POST })
	public ResponseEntity<ReponseMdl> registerAccuracyPhone(@RequestBody UserRegisterRequest userRegisterRequest) {
		return userService.register(userRegisterRequest);
	}
	
	/**
	 * Đăng ký với email (Thực hiện xác thực ở server)
	 * @parram email, password
	 */
//	@RequestMapping(value = Constants.REGISTER_ACCURACY_EMAIL, method = { RequestMethod.POST })
//	public ResponseEntity<ReponseMdl> registerAccuracyEmail(@RequestBody UserRegisterRequest userRegisterRequest) {
//		return userService.register(userRegisterRequest);
//	}
	
	/**
	 * Đăng ký với số điện thoại chưa xác thực ở client (thực hiện xác thực tại server)
	 */
	@RequestMapping(value = Constants.REGISTER_NOT_AUTH_PHONE, method = { RequestMethod.POST })
	public ResponseEntity<ReponseMdl> registerNotAuthPhone(@RequestBody UserRegisterRequest userRegisterRequest) {
		return userService.registerNotAuthPhone(userRegisterRequest);
	}
	
	/**
	 * Người dùng nhập mã xác thực
	 */
	@RequestMapping(value = Constants.USER_AUTH_PHONE, method = { RequestMethod.POST })
	public ResponseEntity<ReponseMdl> userAuthPhone(@RequestBody UserAuthPhoneRequest userAuthPhone) {
		return userService.userAuthPhone(userAuthPhone);
	}
	
	/**
	 * Forgot password
	 */
	@RequestMapping(value = Constants.FORGOT_PASSWORD, method = { RequestMethod.POST })
	public ResponseEntity<ReponseMdl> forgotPassword(@RequestBody String phoneNumber) {
		return userService.forgotPassword(phoneNumber);
	}
	
	/**
	 * Forgot password verifyCode
	 */
	@RequestMapping(value = Constants.FORGOT_PASSWORD_VERIFY_CODE, method = { RequestMethod.POST })
	public ResponseEntity<ReponseMdl> forgotPasswordVerifyCode(@RequestBody UserAuthPhoneRequest userAuthPhone) {
		return userService.forgotPasswordVerifyCode(userAuthPhone);
	}
	
	/**
	 * Forgot password verifyCode
	 */
	@RequestMapping(value = Constants.FORGOT_PASSWORD_MEMBER_UPDATE_PASS, method = { RequestMethod.POST })
	public ResponseEntity<ReponseMdl> forgotPasswordMemberUpdatePass(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		return userService.forgotPasswordMemberUpdatePass(updatePasswordRequest);
	}
	
	/**
	 * Lấy các thông tin cơ bản của user thông qua token
	 */
	@RequestMapping(value = "/user_info", method = { RequestMethod.POST }) 
	public ResponseEntity<ReponseMdl> userInfo(HttpServletRequest request) {
		String usernameAuthen = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.userInfo(usernameAuthen);
	}
	
	/**
	 * Lấy Các thông tin cơ bản của một user khác 
	 */
	@RequestMapping(value = "/get_user_info_by_id", method = { RequestMethod.GET }) 
	public ResponseEntity<ReponseMdl> userInfoById(HttpServletRequest request, @RequestParam("id") Long idUser) {
		return userService.userInfoById(idUser);
	}
	
	/*
	 * Update password
	 */
	@RequestMapping(value = Constants.UPDATE_PASSWORD, method = { RequestMethod.POST }) 
	public ResponseEntity<ReponseMdl> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		return userService.updatePassword(updatePasswordRequest);
	}
	
	/* 
	 * Update password by token
	 */
	@RequestMapping(value = "/update_password_by_token", method = { RequestMethod.POST }) 
	public ResponseEntity<ReponseMdl> updatePasswordByToken(@RequestBody UpdatePasswordByTokenRequest updatePasswordByTokenRequest, HttpServletRequest request) {
		String usernameAuthen = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.updatePasswordByToken(updatePasswordByTokenRequest, usernameAuthen);
	}
	
	/* 
	 * Update user info
	 */
	@RequestMapping(value = "/update_user_info", method = { RequestMethod.POST }) 
	public ResponseEntity<ReponseMdl> updatePasswordByToken(@RequestBody UpdateUserInfoRequest updateUserInfoRequest) {
		String usernameAuthen = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.updateUserInfo(updateUserInfoRequest, usernameAuthen);
	}
}
