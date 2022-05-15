package com.example.sportapp.service;

import com.example.sportapp.entity.Match;
import com.example.sportapp.entity.Team;
import com.example.sportapp.repositories.MatchRepo;
import com.example.sportapp.repositories.TeamRepo;
import com.example.sportapp.repositories.impl.ResultRepoImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface MatchService {

    public Match createMatch(Match match);

    public Date findOne();

}
