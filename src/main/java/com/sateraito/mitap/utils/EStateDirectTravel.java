package com.sateraito.mitap.utils;

public enum EStateDirectTravel {
	/**
	 * 1: người chỉ đường thực hiện đăng ký
	 */
	USER_DIRECT_PERFORM,
	
	/**
	 * 2: được khách du lịch xác nhận
	 */
	USER_TOURIST_CONFIRM,
	/**
	 * 3: Khách du lịch Hủy
	 */
	USER_TOURIST_CANCEL,
	
	/**
	 * 4: đã đăng ký 
	 */
	REGISTER_SUCCESS,
	
	/**
	 * 5: tour du lịch này đã xác định xong người chỉ đường rồi, nhưng vì lý do đột xuất nên gọi điện thông báo với admin để admin thực hiện Hủy (linh động trù tiền của từng đối tượng)
	 */
	ADMIN_CANCEL, 
}
