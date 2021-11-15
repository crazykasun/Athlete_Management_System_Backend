package com.olympic.athletemanagementsystem.result.repository;

import com.olympic.athletemanagementsystem.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    public Result findResultByResultId(Long resultId);

    @Transactional
    @Modifying
    @Query(
            value = "insert into athlete_results values (:athleteId, :resultId)",
            nativeQuery = true
    )
    public int addAthleteResults(@Param("athleteId") Long athleteId, @Param("resultId") Long resultId);
}
