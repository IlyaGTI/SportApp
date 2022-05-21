package com.example.sportapp.entity.dto;


import lombok.*;


import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer id;

    private String nameTeam;

    private Integer points;

    private Integer scored;

    private Integer conceded;

    private Date dateLastGame;

    private Integer playedGames;






    @Override
    public String toString() {
        return "Result{" +
                "nameTeam='" + nameTeam + '\'' +
                ", points=" + points +
                ", scored=" + scored +
                ", conceded=" + conceded +
                ", dateLastGame=" + dateLastGame +
                ", playedGames=" + playedGames +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;
        Result result = (Result) o;
        return nameTeam.equals(result.nameTeam) && points.equals(result.points) && scored.equals(result.scored) && conceded.equals(result.conceded) && dateLastGame.equals(result.dateLastGame) && playedGames.equals(result.playedGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTeam, points, scored, conceded, dateLastGame, playedGames);
    }

    //Объект для передачи в него результата выполнения метода getResult

}
