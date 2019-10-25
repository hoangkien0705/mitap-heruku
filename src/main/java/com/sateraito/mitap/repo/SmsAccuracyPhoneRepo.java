package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.SmsAccuracyPhone;

@Repository
public interface SmsAccuracyPhoneRepo extends JpaRepository<SmsAccuracyPhone, Long> {

	//Kiểm tra sự tồn tại của unique_id
	@Query(value = "SELECT EXISTS (SELECT * FROM sms_accuracy_phone where unique_id = ?1)", nativeQuery = true)
	int checkUniqueIdExists(String unique_id);
	
	@Query(value = "SELECT * FROM sms_accuracy_phone WHERE unique_id = ?1 LIMIT 1", nativeQuery = true)
	SmsAccuracyPhone findOneByUniqueId(String unique_id);
	
	@Query(value = "SELECT * FROM sms_accuracy_phone WHERE user_id = ?1 LIMIT 1", nativeQuery = true)
	SmsAccuracyPhone findOneByUser(long userId);
	
	@Query(value = "SELECT * FROM sms_accuracy_phone WHERE TYPE = ?1 and sms_accuracy_phone.user_id = "
			+ "(SELECT id FROM user WHERE user.phone_number = ?2) ORDER BY create_date DESC limit 1", nativeQuery = true)
	SmsAccuracyPhone findOneByPhoneAndType(int type, String phoneNumber);
}
