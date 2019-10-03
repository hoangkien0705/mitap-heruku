package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.DealHistory;

@Repository
public interface DealHistoryRepo extends JpaRepository<DealHistory, Long> {


}
