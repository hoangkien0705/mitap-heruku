package com.sateraito.mitap.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	
	@Query(value = "select * from role where name = ?1 limit 1", nativeQuery = true)
	Role findByRole(String name);

	@Query(value = "SELECT * from role where id IN (SELECT role_id FROM user_role WHERE user_id = ?1)", nativeQuery = true)
	List<Role> findByUserId(long id);

}
