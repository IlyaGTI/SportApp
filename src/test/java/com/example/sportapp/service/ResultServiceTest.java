package com.example.sportapp.service;

import com.example.sportapp.entity.Match;
import com.example.sportapp.entity.Team;
import com.example.sportapp.entity.dto.Result;
import com.example.sportapp.entity.facade.ResultFacade;
import com.example.sportapp.repositories.TeamRepo;
import com.example.sportapp.repositories.impl.ResultRepoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Resources;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResultServiceTest {

    @Mock
    ResultRepoImpl resultRepo;
    @Mock
    TeamRepo teamRepo;


    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final Date searchingDate = format.parse("2021-09-03");
    private final Date finalDate = format.parse("2021-09-09");
    private final Team testTeam1 = new Team(1, "Тест1", 6, 4, 3, finalDate, 3);
    private final Team testTeam2 = new Team(2, "Тест2", 3, 3, 4, finalDate, 3);
    private final Match match1 = new Match(1, testTeam1, 1, testTeam2, 0, format.parse("2021-09-03"));
    private final Match match2 = new Match(2, testTeam1, 1, testTeam2, 2, format.parse("2021-09-09"));
    private final Match match3 = new Match(1, testTeam1, 2, testTeam2, 1, format.parse("2021-09-15"));

    @InjectMocks
    ResultService resultService;

    ResultServiceTest() throws ParseException {
    }

    @Test
    void findResult() throws ParseException {

        List<Match> matches = new ArrayList<>();
        matches.add(match1);
        matches.add(match2);
        matches.add(match3);

        List<Team> nresults = new ArrayList<>();
        nresults.add(testTeam1);
        nresults.add(testTeam2);

        List<Result> emtyResult = new ArrayList<>();
        emtyResult.add(new Result(1,"Тест1", 0, 0, 0, finalDate, 0));
        emtyResult.add(new Result(2,"Тест2", 0, 0, 0, finalDate, 0));


        when(resultRepo.findBetweenTwoDates(searchingDate, finalDate)).thenReturn(matches);

        when(resultRepo.findLastDateMatch()).thenReturn(format.parse("2021-09-03"));

        List<Result> fresults = ResultFacade.emptyListResult(nresults);

        List<Result> results = resultService.findResult("2021-09-09");

        List<Integer> points = new ArrayList<>();
        points.add(results.get(0).getPoints());
        points.add(results.get(1).getPoints());

        assertThat(points.get(0)).isEqualTo(3);
        assertThat(points.get(1)).isEqualTo(3);

        Mockito.verify(resultRepo, Mockito.times(1)).findBetweenTwoDates(searchingDate, finalDate);
        Mockito.verify(teamRepo, Mockito.times(1)).resultNow();
    }
}