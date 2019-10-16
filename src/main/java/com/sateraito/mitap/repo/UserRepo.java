package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.MitapUser;

@Repository
public interface UserRepo extends JpaRepository<MitapUser, Long> {

	@Query(value = "select * from user where email = ?1", nativeQuery = true)
	MitapUser findByEmail(String username);

	@Query(value = "select * from user where phone_number = ?1 limit 1", nativeQuery = true)
	MitapUser findByPhoneNumber(String username);

	@Query(value = "select * from user where username = ?1", nativeQuery = true)
	MitapUser findByUsername(String username);

	//Kiểm tra sự tồn tại của unique_id
	@Query(value = "SELECT EXISTS (SELECT * FROM user where unique_id = ?1)", nativeQuery = true)
	int checkUniqueIdExists(String unique_id);
	
	//Kiểm tra sự tồn tại của username
	@Query(value = "SELECT EXISTS (SELECT * FROM user where username = ?1)", nativeQuery = true)
	int checkUniqueUsernameExists(String username);
}
