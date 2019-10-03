package com.sateraito.mitap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static final String APP_TOKEN = "70fe51c1d914eed09b4d105df27c22ec";

	public static boolean checkMD5(String md5, File updateFile) {
		if ((md5 != null && md5 != "") || updateFile == null) {
			Log.d("MD5 string empty or updateFile null");
			return false;
		}

		String calculatedDigest = calculateMD5(updateFile);
		if (calculatedDigest == null) {
			Log.d("calculatedDigest null");
			return false;
		}

		Log.d("Calculated digest: " + calculatedDigest);
		Log.d("Provided digest: " + md5);

		return calculatedDigest.equalsIgnoreCase(md5);
	}

	public static String calculateMD5(File updateFile) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			Log.d("Exception while getting digest");
			return null;
		}

		InputStream is;
		try {
			is = new FileInputStream(updateFile);
		} catch (FileNotFoundException e) {
			Log.d("Exception while getting FileInputStream");
			return null;
		}

		byte[] buffer = new byte[8192];
		int read;
		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			byte[] md5sum = digest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			String output = bigInt.toString(16);
			// Fill to 32 chars
			output = String.format("%32s", output).replace(' ', '0');
			return output;
		} catch (IOException e) {
			throw new RuntimeException("Unable to process file for MD5", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.d("Exception on closing MD5 input stream");
			}
		}
	}

	public static final String convertToMD5(String input) {
		if (input == null)
			return input;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytesOfMessage = input.getBytes("UTF-8");
			byte[] thedigest = md5.digest(bytesOfMessage);
			StringBuilder sb = new StringBuilder();
			for (byte b : thedigest) {
				sb.append(String.format("%02X", b & 0xff));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean matching(String orig, String compare) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(compare.getBytes());
			byte[] digest = md.digest();
			md5 = new BigInteger(1, digest).toString(16);

			return md5.equalsIgnoreCase(orig);

		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	}
	
	public static String decode(String md5) {
		try {
			@SuppressWarnings("unused")
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md5;
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}