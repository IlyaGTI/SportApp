package com.example.sportapp.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String nameTeam;

    private Integer points;

    private Integer scored;

    private Integer conceded;

    private Date dateLastGame;

    private Integer playedGames;

}
