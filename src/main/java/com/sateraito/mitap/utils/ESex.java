package com.sateraito.mitap.utils;

public enum ESex {
	BOY("Nam"), GIRL("Nữ"), GAY("Đồng tính");
	
	private String value;

	private ESex(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
