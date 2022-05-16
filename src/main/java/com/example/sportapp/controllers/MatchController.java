package com.example.sportapp.controllers;

import com.example.sportapp.entity.Match;
import com.example.sportapp.repositories.MatchRepo;
import com.example.sportapp.service.MatchService;
import com.example.sportapp.service.impl.MatchServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {

    private MatchServiceImpl matchService;

    @PostMapping("/new")
    public ResponseEntity<Match> newMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.createMatch(match));
    }

    @GetMapping("/search/{date}")
    public ResponseEntity<List<Match>> findMatchByDate(@PathVariable String date) throws ParseException {
        return ResponseEntity.ok(matchService.findMatchByDate(date));
    }

}
