package com.example.sportapp.repositories.result;

import com.example.sportapp.entity.Match;

import java.util.Date;
import java.util.List;

public interface MatchResultRepo {

    Date findLastDateMatch();

    List<Match> findBetweenTwoDates(Date date1, Date date2);
}
