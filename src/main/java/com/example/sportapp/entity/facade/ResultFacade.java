package com.example.sportapp.entity.facade;

import com.example.sportapp.entity.Team;
import com.example.sportapp.entity.dto.Result;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ResultFacade {
    public static Result TeamToResult(Team team){
        Result result = new Result();
        result.setId(team.getId());
        result.setNameTeam(team.getNameTeam());
        result.setScored(team.getScored());
        result.setConceded(team.getConceded());
        result.setPoints(team.getPoints());
        result.setPlayedGames(team.getPlayedGames());
        result.setDateLastGame(team.getDateLastGame());
        return result;
    }

    public static List<Result> emptyListResult(List<Team> teams){
        return teams.stream()
                .map(p -> new Result(p.getId(), p.getNameTeam(), 0, 0, 0,
                        p.getDateLastGame(), 0))
                .collect(Collectors.toList());
    }

    public static List<Result> listTeamToListResult(List<Team> teams){
        return teams.stream()
                .map(p -> new Result(p.getId(),p.getNameTeam(), p.getScored(), p.getConceded(), p.getPoints(), p.getDateLastGame(), p.getPlayedGames())).collect(Collectors.toList());
    }
}
