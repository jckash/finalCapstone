package com.devmountain.musicTimeCapsule.repositories;

import com.devmountain.musicTimeCapsule.entities.Memories;
import com.devmountain.musicTimeCapsule.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoriesRepository extends JpaRepository<Memories, Long> {
    List<Memories> findAllByUserEquals(Users users);
}
