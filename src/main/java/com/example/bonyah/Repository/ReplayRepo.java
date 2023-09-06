package com.example.bonyah.Repository;

import com.example.bonyah.Models.Replay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplayRepo extends JpaRepository<Replay,Integer> {
    Replay findReplayById(Integer id);
}
