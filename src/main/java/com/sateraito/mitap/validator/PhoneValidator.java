package com.sateraito.mitap.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
	private static final String PHONE_REGEX = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";

	private static Pattern pattern;

	private Matcher matcher;

	public PhoneValidator() {
		pattern = Pattern.compile(PHONE_REGEX, Pattern.CASE_INSENSITIVE);
	}

	public boolean validatePhone(String phone) {
		matcher = pattern.matcher(phone);
		return matcher.matches();
	}
}
