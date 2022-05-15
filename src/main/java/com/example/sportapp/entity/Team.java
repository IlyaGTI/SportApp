package com.example.sportapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Table(name = "teams")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name_team", nullable = false, length = 45)
    private String nameTeam;

    @Column(name = "points")
    private Integer points;

    @Column(name = "scored")
    private Integer scored;

    @Column(name = "conceded")
    private Integer conceded;

    @Column(name = "date_last_game")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateLastGame;

    @Column(name = "played_games")
    private Integer playedGames;

   public Team(String nameTeam) {
       this.nameTeam = nameTeam;
  }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return id != null && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}