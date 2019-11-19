package com.sateraito.mitap.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sateraito.mitap.utils.Log;

public class PhoneValidator {
//	private static final String PHONE_REGEX = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$"; //check số điện thoại trong nước
	private static final String PHONE_REGEX = "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?"; //check số điện thoại quốc tế

	private static Pattern pattern;

	private Matcher matcher;

	public PhoneValidator() {
		pattern = Pattern.compile(PHONE_REGEX, Pattern.CASE_INSENSITIVE);
	}

	public boolean validatePhone(String phone) {
		matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
	//test
	public static void main(String[] args) {
		PhoneValidator validator = new PhoneValidator();
		Log.d(validator.validatePhone("+84969951417") + "");
	}
}


