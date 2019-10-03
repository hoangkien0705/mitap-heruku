package com.sateraito.mitap.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.UserRole;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

	@Query(value = "select name from role where id in (select role_id from user_role where user_id = ?1)", nativeQuery = true)
	List<String> findRoleNameByUserId(long id);

}
