package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.UserDirection;

@Repository
public interface UserDirectionRepo extends JpaRepository<UserDirection, Long> {

	@Query(value = "SELECT * FROM user_direction WHERE id_user = ?1 LIMIT 1", nativeQuery = true)
	UserDirection findOneByUserId(long id);


}
