package com.sateraito.mitap.utils;

public enum ESMSTypeAccuracyPhone {
	/**
	 * Trong trường hợp người dùng đăng ký tài khoản xác thực số điện thoại
	 */
	REGISTER_VERIFY_PHONENUMBER(1),
	/**
	 * Trong trường hợp người dùng quên mật khẩu, thực hiện gửi code về số điện thoại của khách 
	 */
	FORGOT_PASSWORD(2);
	
	private int type = 1;
	
	

	private ESMSTypeAccuracyPhone(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
