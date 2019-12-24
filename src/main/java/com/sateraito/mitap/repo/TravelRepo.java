package com.sateraito.mitap.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.Travel;

@Repository
public interface TravelRepo extends JpaRepository<Travel, Long> {
	
	@Query(value = "select * from travel where state = ?1", nativeQuery = true)
	List<Travel> findByStateTravel(int state);

	@Query(value = "SELECT EXISTS (SELECT * FROM travel where unique_id = ?1)", nativeQuery = true)
	int checkUniqueIdExists(String uniqueId);

}
