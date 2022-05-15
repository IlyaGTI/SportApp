package com.example.sportapp.controllers;

import com.example.sportapp.entity.Team;
import com.example.sportapp.entity.dto.Result;
import com.example.sportapp.service.ResultService;
import com.example.sportapp.service.TeamService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;
    private ResultService resultService;

    @PostMapping("/new")
    public ResponseEntity<Team> createNewTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.createTeam(team));
    }
    @GetMapping("/period/{date}")
    public ResponseEntity<List<Result>> findResult(@PathVariable String date) throws ParseException {
        return ResponseEntity.ok(resultService.findResult(date));
    }
    @GetMapping("/result")
    public ResponseEntity<List<Team>> result(){
        return ResponseEntity.ok(teamService.getTable());
    }
}
