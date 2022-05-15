package com.example.sportapp.entity.facade;

import com.example.sportapp.entity.Team;
import com.example.sportapp.entity.dto.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultFacade {
    public static Result TeamToResult(Team team){
        Result result = new Result();
        result.setNameTeam(team.getNameTeam());
        result.setScored(team.getScored());
        result.setConceded(team.getConceded());
        result.setPoints(team.getPoints());
        result.setPlayedGames(team.getPlayedGames());
        result.setDateLastGame(team.getDateLastGame());
        return result;
    }
}
