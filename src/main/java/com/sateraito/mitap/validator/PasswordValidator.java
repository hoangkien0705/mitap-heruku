package com.sateraito.mitap.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	/**
			^                 # start-of-string
			(?=.*[0-9])       # a digit must occur at least once
			(?=.*[a-z])       # a lower case letter must occur at least once
			(?=.*[A-Z])       # an upper case letter must occur at least once
			(?=.*[@#$%^&+=])  # a special character must occur at least once
			(?=\S+$)          # no whitespace allowed in the entire string
			.{8,}             # anything, at least eight places though
			$                 # end-of-string
	 */
	private static final String PASSWORD_REGEX = "^(?=.*[0-9])"
			+ "(?=.*[a-z])"
//			+ "(?=.*[A-Z])" 
//			+ "(?=.*[@#$%^&+=])"
			+ "(?=\\S+$).{8,}$";
	/*
	 * Có ít nhất 1 ký tự số
	 * có ít nhất 1 ký tự in thường
	 * có ít nhất 1 ký tự in hoa
	 * có ít nhất 1 ký tự đặc biệt
	 * không được phép chứa khoảng trắng
	 * có tối thiểu là 8 ký tự
	 */

	private static Pattern pattern;

	private Matcher matcher;

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
	}

	public boolean validatePassword(String password) {
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
