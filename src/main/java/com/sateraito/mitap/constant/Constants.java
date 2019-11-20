package com.sateraito.mitap.constant;

import java.text.SimpleDateFormat;

import com.google.gson.Gson;

public class Constants {
	public static final Gson GSON = new Gson();
	public static final SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat fomat_1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static final SimpleDateFormat fomat_2 = new SimpleDateFormat("dd/MM/yyyy");
	public static final String API_PUBLIC = "/api/public";
	public static final String API_PRIVATE = "/api/private";
	public static final String API_REDIRECT = "/api/redirect";
	public static final String CONFIRM_REGISTER = "/confirm_register";
	public static final String UPDATE_PASSWORD = "/update_password";
	public static final String REGISTER_COMPACT = "/register_compact";
	public static final String REGISTER_SOCIAL = "/register_social";
	public static final String LOGIN = "/login";
	public static final String REGISTER_ACCURACY_PHONE = "/register_accuracy_phone";
	public static final String REGISTER_NOT_AUTH_PHONE = "/register_not_auth_phone";
	public static final String USER_AUTH_PHONE = "/user_register_auth_phone";
	public static final String REGISTER_ACCURACY_EMAIL = "/register_accuracy_email";
	public static final String NEW_PASSWORD = "/new_password";
	public static final String FORGOT_PASSWORD = "/forgot_password";
	public static final String FORGOT_PASSWORD_VERIFY_CODE = "/forgot_password_verify_code";
	public static final String FORGOT_PASSWORD_MEMBER_UPDATE_PASS = "/forgot_password_member_update_pass";
	public static final String LIMIT = "limit";
	
	public static final String CODE_VERIFY = "M-%s";
	public static final String SYNTAX_SMS_VERIFY_CODE = CODE_VERIFY + " is your Mitap verification code";
	public static final long TIME_EXPIRED_VERIFY_CODE = 60; //thời gian chờ user xác thực là 60 phút
}
