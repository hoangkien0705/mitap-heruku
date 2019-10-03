package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.TouristRatingDirection;

@Repository
public interface TouristRatingDirectionRepo extends JpaRepository<TouristRatingDirection, Long> {


}
