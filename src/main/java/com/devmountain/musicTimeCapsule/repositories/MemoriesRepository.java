package com.devmountain.musicTimeCapsule.repositories;

import com.devmountain.musicTimeCapsule.entities.Memories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoriesRepository extends JpaRepository<Memories, Long> {
}
