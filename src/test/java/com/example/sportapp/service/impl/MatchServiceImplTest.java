package com.example.sportapp.service.impl;

import com.example.sportapp.entity.Match;
import com.example.sportapp.entity.Team;
import com.example.sportapp.repositories.MatchRepo;
import com.example.sportapp.repositories.TeamRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatchServiceImplTest {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private final Date finalDate = format.parse("2021-09-01");
    private final Team testTeam1 = new Team(1, "Тест1", 6, 4, 3, finalDate, 3);
    private final Team testTeam2 = new Team(2, "Тест2", 3, 3, 4, finalDate, 3);
    @InjectMocks
    MatchServiceImpl matchService;
    @Mock
    TeamRepo teamRepo;
    @Mock
    MatchRepo matchRepo;

    MatchServiceImplTest() throws ParseException {
    }

    @Test
    void createMatch() throws ParseException {
        List<Team> testing = new ArrayList<>();
        testing.add(testTeam1);
        testing.add(testTeam2);
        Match test = new Match(1, testTeam1, 1, testTeam2, 0, format.parse("2021-09-03"));
        Match trying = matchService.createMatch(test);
        assertThat(testing.get(0).getPoints()).isEqualTo(9);
        assertThat(testing.get(0).getScored()).isEqualTo(5);
    }
    
}