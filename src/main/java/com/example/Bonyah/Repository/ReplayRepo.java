package com.example.Bonyah.Repository;

import com.example.Bonyah.Models.Replay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplayRepo extends JpaRepository<Replay,Integer> {
    Replay findReplayById(Integer id);
}
