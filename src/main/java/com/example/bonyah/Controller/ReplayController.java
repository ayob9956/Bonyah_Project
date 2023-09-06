package com.example.bonyah.Controller;

import com.example.bonyah.Api.ApiResponse;
import com.example.bonyah.Models.Replay;
import com.example.bonyah.Service.ReplayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/replay")
@RequiredArgsConstructor
public class ReplayController {
    private final ReplayService replayService;

    @GetMapping("/get-all")
    public ResponseEntity getAllReplay(){
        return ResponseEntity.status(200).body(replayService.getAllReplay());
    }

    @PostMapping("/add")
    public ResponseEntity addReplay(@RequestBody @Valid Replay replay){
        replayService.addReplay(replay);
        return ResponseEntity.status(200).body(new ApiResponse("Replay added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateReplay(@PathVariable Integer id,@RequestBody @Valid Replay replay){
        replayService.updateReplay(id,replay);
        return ResponseEntity.status(200).body(new ApiResponse("Replay updated"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReplay(@PathVariable Integer id){
        replayService.deleteReplay(id);
        return ResponseEntity.status(200).body(new ApiResponse("Replay deleted"));

    }
}
