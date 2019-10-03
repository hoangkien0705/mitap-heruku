package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.PlaceTravel;

@Repository
public interface PlaceTravelRepo extends JpaRepository<PlaceTravel, Long> {


}
