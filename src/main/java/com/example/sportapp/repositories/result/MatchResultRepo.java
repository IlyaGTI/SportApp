package com.example.sportapp.repositories.result;

import com.example.sportapp.entity.Match;

import java.util.Date;
import java.util.List;

public interface MatchResultRepo {

    //Поиск последней даты
    Date findLastDateMatch();

    //Вывод матчей между последней датой и датой введной пользователем
    List<Match> findBetweenTwoDates(Date date1, Date date2);

    List<Match> findAll();


}
