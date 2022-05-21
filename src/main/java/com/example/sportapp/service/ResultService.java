package com.example.sportapp.service;

import com.example.sportapp.entity.Match;
import com.example.sportapp.entity.Team;
import com.example.sportapp.entity.dto.Result;
import com.example.sportapp.entity.facade.ResultFacade;
import com.example.sportapp.repositories.TeamRepo;
import com.example.sportapp.repositories.impl.ResultRepoImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ResultService {

    private ResultRepoImpl resultRepo;
    private TeamRepo teamRepo;

    //Вывод состояние турнирной таблицы на опреденную дату
    public List<Result> findResult(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fin = format.parse(date);
        Date str = resultRepo.findLastDateMatch();

        java.sql.Date finish = new java.sql.Date(fin.getTime());
        java.sql.Date start = new java.sql.Date(str.getTime());

        List<Match> search = resultRepo.findBetweenTwoDates(start, finish);

        List<Result> result = ResultFacade.emptyListResult(teamRepo.resultNow());

        Map<Integer, Result> mapResult = result.stream()
                .collect(Collectors.toMap(Result :: getId, Function.identity()));

        List<Result> finalResult = new ArrayList<>();


       for (Match match : search) {

               Result home = mapResult.get(match.getHomeTeam().getId());
               Result away = mapResult.get(match.getAwayTeam().getId());


           if (match.getHomeTeamGoal() > match.getAwayTeamGoal()) {
               home.setPoints(home.getPoints() + 3);

           }
           if (match.getHomeTeamGoal() < match.getAwayTeamGoal()) {
               away.setPoints(away.getPoints() + 3);
           }
           if (match.getHomeTeamGoal().equals(match.getAwayTeamGoal())) {
               home.setPoints(home.getPoints() + 1);
               away.setPoints(away.getPoints() + 1);
           }

           home.setDateLastGame(match.getDateMatch());
           away.setDateLastGame(match.getDateMatch());

           home.setScored(home.getScored() + match.getHomeTeamGoal());
           away.setScored(away.getScored() + match.getAwayTeamGoal());

           home.setConceded(home.getConceded() + match.getAwayTeamGoal());
           away.setConceded(away.getConceded() + match.getHomeTeamGoal());


           home.setPlayedGames(home.getPlayedGames() + 1);
           away.setPlayedGames(away.getPlayedGames() + 1);

           finalResult.add(home);
           finalResult.add(away);

       }

        return  finalResult.stream()
                .filter(p -> p.getDateLastGame().equals(finish) || p.getDateLastGame().before(finish))
                .sorted(Comparator.comparingInt(Result::getPoints).reversed()).distinct()
                .collect(Collectors.toList());
    }
}
