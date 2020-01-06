package com.sateraito.mitap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sateraito.mitap.entity.LevelJapanese;

@Repository
public interface LevelJapaneseRepo extends JpaRepository<LevelJapanese, Long> {


}
