package com.example.sportapp.controllers;

import com.example.sportapp.entity.Match;
import com.example.sportapp.repositories.MatchRepo;
import com.example.sportapp.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @PostMapping("/new")
    public ResponseEntity<Match> newMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.createMatch(match));
    }

    @GetMapping("/all")
    public ResponseEntity<Date> dare(){
        return ResponseEntity.ok(matchService.findOne());
    }

}
