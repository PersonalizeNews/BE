package com.amcamp.domain.track.dao;

import com.amcamp.domain.track.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
