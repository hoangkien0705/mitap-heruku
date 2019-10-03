package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.DirectTravel;

@Repository
public interface DirectTravelRepo extends JpaRepository<DirectTravel, Long> {


}
