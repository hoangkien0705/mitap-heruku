package com.sateraito.mitap.constant;

import java.text.SimpleDateFormat;

import com.google.gson.Gson;

public class Constants {
	public static final Gson GSON = new Gson();
	public static final SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat fomat_1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static final String API_PUBLIC = "/api/public";
	public static final String API_PRIVATE = "/api/private";
	public static final String API_REDIRECT = "/api/redirect";
	public static final String CONFIRM_REGISTER = "/confirm_register";
	public static final String UPDATE_PASSWORD = "/update_password";
	public static final String FORGET_PASSWORD = "/forget_password";
	public static final String REGISTER_COMPACT = "/register_compact";
	public static final String REGISTER_SOCIAL = "/register_social";
	public static final String LOGIN = "/login";
	public static final String REGISTER_ACCURACY_PHONE = "/register_accuracy_phone";
	public static final String REGISTER_ACCURACY_EMAIL = "/register_accuracy_email";
	public static final String NEW_PASSWORD = "/new_password";
	public static final String LIMIT = "limit";
}
