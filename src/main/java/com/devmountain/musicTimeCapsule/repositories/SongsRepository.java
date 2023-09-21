package com.devmountain.musicTimeCapsule.repositories;

import com.devmountain.musicTimeCapsule.entities.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongsRepository extends JpaRepository<Songs, Long> {
}
