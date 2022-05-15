package com.example.sportapp.repositories;

import com.example.sportapp.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {

    @Query("select t from Team as t order by t.points DESC ")
    List<Team> resultNow();

}
