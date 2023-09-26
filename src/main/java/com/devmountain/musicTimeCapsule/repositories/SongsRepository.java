package com.devmountain.musicTimeCapsule.repositories;

import com.devmountain.musicTimeCapsule.entities.Songs;
import com.devmountain.musicTimeCapsule.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongsRepository extends JpaRepository<Songs, Long> {
    List<Songs> findAllByUserEquals(Users users);
}
