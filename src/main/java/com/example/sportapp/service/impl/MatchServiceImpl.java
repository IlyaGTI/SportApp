package com.example.sportapp.service.impl;

import com.example.sportapp.entity.Match;
import com.example.sportapp.entity.Team;
import com.example.sportapp.repositories.MatchRepo;
import com.example.sportapp.repositories.TeamRepo;
import com.example.sportapp.repositories.impl.ResultRepoImpl;
import com.example.sportapp.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {

    private TeamRepo teamRepo;
    private MatchRepo matchRepo;
    private ResultRepoImpl resultRepo;

    @Override
    public Match createMatch(Match match) {
        Team home = match.getHomeTeam();
        Team away = match.getAwayTeam();

        if(match.getHomeTeamGoal() > match.getAwayTeamGoal()){
            home.setPoints(home.getPoints() + 3);
        }
        if(match.getHomeTeamGoal() < match.getAwayTeamGoal()){
            away.setPoints(away.getPoints() + 3);
        }
        if(match.getHomeTeamGoal() == match.getAwayTeamGoal()){
            home.setPoints(home.getPoints() + 1);
            away.setPoints(away.getPoints() + 1);
        }

        home.setPlayedGames(home.getPlayedGames() + 1);
        away.setPlayedGames(away.getPlayedGames() + 1);

        home.setScored(home.getScored() + match.getHomeTeamGoal());
        away.setScored(away.getScored() + match.getAwayTeamGoal());

        home.setConceded(home.getConceded() + match.getAwayTeamGoal());
        away.setConceded(away.getConceded() + match.getHomeTeamGoal());

        home.setDateLastGame(match.getDateMatch());
        away.setDateLastGame(match.getDateMatch());

        teamRepo.save(home);
        teamRepo.save(away);

        return matchRepo.save(match);
    }

    @Override
    @Transactional(readOnly = true)
    public Date findOne() {
        return resultRepo.findLastDateMatch();
    }

    @Override
    public List<Match> findMatchByDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date searching = format.parse(date);


        return matchRepo.findAllByDateMatch(searching);
    }

}
