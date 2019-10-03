package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.Place;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Long> {


}
