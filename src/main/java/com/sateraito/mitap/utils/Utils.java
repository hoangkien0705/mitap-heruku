package com.sateraito.mitap.utils;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sateraito.mitap.constant.Constants;

public class Utils {
	
	public static String generateUniqueId() {
		String easy = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
		RandomString tickets = new RandomString(40, new SecureRandom(), easy);
		String key = tickets.nextString();
		return key;
	}
	
	public static String fomatDateToString(Date date) {
		return date != null ? Constants.fomat.format(date) : "";
	}
	
	public static String fomat1DateToString(Date date) {
		return date != null ? Constants.fomat_1.format(date) : "";
	}
	
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	boolean match = bCryptPasswordEncoder.matches("123456Aa", "$2a$10$N0WaqsnynbeGaHL5GDXZT.sxcvulTaJ1P6hIcGBS5aIyC3Kr29XSe");
    	System.out.println(match);
    	
    	try {
			long diff = (new Date()).getTime() - Constants.fomat_1.parse("2019-10-16 13:37:10").getTime();
			long second = diff / 1000;
			long minute = diff / (60 * 1000);
			System.out.println(diff + " " + second + " " + minute);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
