package com.example.sportapp.service;

import com.example.sportapp.entity.Team;
import com.example.sportapp.repositories.TeamRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TeamService {

    private TeamRepo teamRepo;

    public Team createTeam(Team team){
        return teamRepo.save(team);
    }

    @Transactional(readOnly = true)
    public List<Team> getTable(){
        return teamRepo.resultNow();
    }
}
