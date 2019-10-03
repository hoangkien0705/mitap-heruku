package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.PriceService;

@Repository
public interface PriceServiceRepo extends JpaRepository<PriceService, Long> {


}
