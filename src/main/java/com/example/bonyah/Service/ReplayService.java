package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Models.Replay;
import com.example.bonyah.Repository.ReplayRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplayService {
    private final ReplayRepo replayRepo;

    public List<Replay>getAllReplay(){
        return replayRepo.findAll();
    }
    public void addReplay(Replay replay){
        replayRepo.save(replay);
    }
    public void updateReplay(Integer id,Replay replay){
        Replay replay1=replayRepo.findReplayById(id);
        if (replay1==null){
            throw new ApiException("id not founded");
        }
        replay1.setTitle(replay.getTitle());
        replay1.setBody(replay.getBody());
        replay1.setRating(replay.getRating());
        replayRepo.save(replay1);
    }
    public void deleteReplay(Integer id){
        Replay replay = replayRepo.findReplayById(id);
        if (replay==null){
            throw new ApiException("id not founded");
        }
        replayRepo.delete(replay);
    }
}
