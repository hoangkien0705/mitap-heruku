package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.DirectTravel;

@Repository
public interface DirectTravelRepo extends JpaRepository<DirectTravel, Long> {

	@Query(value = "SELECT EXISTS (SELECT * FROM direct_travel where unique_id = ?1)", nativeQuery = true)
	int checkUniqueDirectTravelExists(String uniqueDirectTravel);
	
	@Query(value = "SELECT EXISTS (SELECT * FROM direct_travel where id_user = ?1 and id_travel = ?2 )", nativeQuery = true)
	int checkDirectTravelExists(long idUser, long idTravel);


}
