package com.example.sportapp.repositories.impl;

import com.example.sportapp.entity.Match;
import com.example.sportapp.repositories.result.MatchResultRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
@Repository
public class ResultRepoImpl implements  MatchResultRepo {

    private final EntityManager entityManager;

    public ResultRepoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public Date findLastDateMatch() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Date> criteriaQuery = criteriaBuilder.createQuery(Date.class);
        Root<Match> matchRoot = criteriaQuery.from(Match.class);
        criteriaQuery.select(matchRoot.get("dateMatch"));
        criteriaQuery.orderBy(criteriaBuilder.desc(matchRoot.get("dateMatch")));

        return entityManager.createQuery(criteriaQuery).getResultList().get(0);
    }

    @Override
    public List<Match> findBetweenTwoDates(Date date1, Date date2) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Match> criteriaQuery = criteriaBuilder.createQuery(Match.class);
        Root<Match> matchRoot = criteriaQuery.from(Match.class);
        criteriaQuery.select(matchRoot);
        criteriaQuery.where(criteriaBuilder.between(matchRoot.get("dateMatch"), date1, date2));
        criteriaQuery.orderBy(criteriaBuilder.desc(matchRoot.get("dateMatch")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Match> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Match> criteriaQuery = criteriaBuilder.createQuery(Match.class);
        Root<Match> matchRoot = criteriaQuery.from(Match.class);
        criteriaQuery.select(matchRoot);
        criteriaQuery.orderBy(criteriaBuilder.desc(matchRoot.get("dateMatch")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
