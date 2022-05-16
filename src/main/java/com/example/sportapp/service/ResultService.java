package com.example.sportapp.service;

import com.example.sportapp.entity.Match;
import com.example.sportapp.entity.dto.Result;
import com.example.sportapp.entity.facade.ResultFacade;
import com.example.sportapp.repositories.impl.ResultRepoImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ResultService {

    private ResultRepoImpl resultRepo;

    //Вывод состояние турнирной таблицы на опреденную дату
    public List<Result> findResult(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fin = format.parse(date);
        Date str = resultRepo.findLastDateMatch();

        java.sql.Date finish = new java.sql.Date(fin.getTime());
        java.sql.Date start = new java.sql.Date(str.getTime());

        List<Match> search = resultRepo.findBetweenTwoDates(finish, start);

        List<Result> result = new ArrayList<>();

        search.forEach(match1 -> {

            Result home = ResultFacade.TeamToResult(match1.getHomeTeam());
            Result away = ResultFacade.TeamToResult(match1.getAwayTeam());

            if(match1.getHomeTeamGoal() > match1.getAwayTeamGoal()){
                home.setPoints(home.getPoints() - 3);
            }
            if(match1.getHomeTeamGoal() < match1.getAwayTeamGoal()){
                away.setPoints(away.getPoints() - 3);
            }
            if(match1.getHomeTeamGoal().equals(match1.getAwayTeamGoal())){
                home.setPoints(home.getPoints() - 1);
                away.setPoints(away.getPoints() - 1);
            }

            home.setScored(home.getScored() - match1.getHomeTeamGoal());
            away.setScored(away.getScored() - match1.getAwayTeamGoal());

            home.setConceded(home.getConceded() - match1.getAwayTeamGoal());
            away.setConceded(away.getConceded() - match1.getHomeTeamGoal());

            home.setPlayedGames(home.getPlayedGames() - 1);
            away.setPlayedGames(away.getPlayedGames() - 1);

            home.setDateLastGame(match1.getDateMatch());
            away.setDateLastGame(match1.getDateMatch());

            result.add(home);
            result.add(away);

        } );

        return result.stream()
                .filter(p -> p.getDateLastGame().equals(finish))
                .sorted(Comparator.comparingInt(Result::getPoints).reversed()).
                collect(Collectors.toList());
    }
}
