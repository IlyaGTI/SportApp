package com.example.sportapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Table(name = "`matchs`", indexes = {
        @Index(name = "id_away_idx", columnList = "away_team_id"),
        @Index(name = "id_home_idx", columnList = "home_team_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @Column(name = "home_team_goal", nullable = false)
    private Integer homeTeamGoal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(name = "away_team_goal", nullable = false)
    private Integer awayTeamGoal;

    @Column(name = "date_match", nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dateMatch;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return id.equals(match.id) && Objects.equals(homeTeam, match.homeTeam) && homeTeamGoal.equals(match.homeTeamGoal) && Objects.equals(awayTeam, match.awayTeam) && awayTeamGoal.equals(match.awayTeamGoal) && dateMatch.equals(match.dateMatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeTeam, homeTeamGoal, awayTeam, awayTeamGoal, dateMatch);
    }
}