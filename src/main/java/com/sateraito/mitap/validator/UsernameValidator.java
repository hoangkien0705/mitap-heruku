package com.sateraito.mitap.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {
	/**
	 * ^ # start-of-string (?=.*[0-9]) # a digit must occur at least once
	 * (?=.*[a-z]) # a lower case letter must occur at least once (?=.*[A-Z]) # an
	 * upper case letter must occur at least once (?=.*[@#$%^&+=]) # a special
	 * character must occur at least once (?=\S+$) # no whitespace allowed in the
	 * entire string .{8,} # anything, at least eight places though $ #
	 * end-of-string
	 */
	private static final String USERNAME_REGEX = "^(?=\\\\S+$).{8,}$"; //không được chứa khoảng trắng và ít nhất từ 8 ký tự đổ lên

	private static Pattern pattern;

	private Matcher matcher;

	public UsernameValidator() {
		pattern = Pattern.compile(USERNAME_REGEX, Pattern.CASE_INSENSITIVE);
	}

	public boolean validateUsername(String password) {
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
}

