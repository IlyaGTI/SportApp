package com.example.sportapp.repositories;

import com.example.sportapp.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {

    List<Match> findAllByDateMatch(LocalDate date);

}
