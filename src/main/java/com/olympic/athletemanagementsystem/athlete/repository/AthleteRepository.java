package com.olympic.athletemanagementsystem.athlete.repository;

import com.olympic.athletemanagementsystem.athlete.dto.AthleteDTO;
import com.olympic.athletemanagementsystem.athlete.dto.AthleteEventsOnlyDTO;
import com.olympic.athletemanagementsystem.athlete.dto.ImageOnlyDTO;
import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Page<AthleteDTO> findAthleteByGender_Id(Pageable pageable, Long id);
    Athlete findAthleteByAthleteId(Long athleteId);

    @Query(
            value = "FROM Athlete a JOIN FETCH a.events e WHERE e.enabled = :enabled",
            countQuery = "select count(a) FROM Athlete a LEFT JOIN a.events e WHERE e.enabled = :enabled"
    )
    Page<AthleteEventsOnlyDTO> findAthleteByEventsEnabled(Pageable pageable, @Param("enabled") boolean enabled);
    Page<AthleteDTO> findAthleteByFirstNameContainsAndLastNameContains(Pageable pageable, String firstName, String lastName);
    ImageOnlyDTO findAthleteByAthleteIdAndFirstNameContains(Long athleteId, String firstName);

    @Query(
            value = "FROM Athlete a JOIN FETCH a.events e JOIN FETCH a.gender g WHERE e.eventId = :eventId " +
                    "AND g.id = :genderId AND a.country LIKE %:country% AND a.firstName LIKE %:firstName%",
            countQuery = "select count(a) FROM Athlete a LEFT JOIN a.events e LEFT JOIN a.gender g WHERE e.eventId = :eventId " +
                    "AND g.id = :genderId AND a.country LIKE %:country% AND a.firstName LIKE %:firstName%"
    )
    Page<Athlete> searchAthlete(Pageable pageable, @Param("eventId") Long eventId,
                                            @Param("genderId") Long genderId,
                                            @Param("country") String country,
                                            @Param("firstName") String firstName);


    @Query(
            value = "FROM Athlete a JOIN FETCH a.gender g WHERE g.id = :genderId AND a.country LIKE %:country% AND a.firstName LIKE %:firstName%",
            countQuery = "select count(a) FROM Athlete a INNER JOIN a.gender g WHERE g.id = :genderId AND a.country LIKE %:country% AND a.firstName LIKE %:firstName%"
    )
    Page<Athlete> searchAthleteWithoutEvents(Pageable pageable, @Param("genderId") Long genderId,
                                             @Param("country") String country,
                                             @Param("firstName") String firstName);

    @Transactional
    @Modifying
    @Query(
            value = "insert into athlete_events values (:athleteId, :eventId)",
            nativeQuery = true
    )
    public int addAthleteEvent(@Param("athleteId") Long athleteId, @Param("eventId") Long eventId);

    @Transactional
    @Modifying
    @Query(
            value = "delete from athlete_events  where athlete_id = :athleteId",
            nativeQuery = true
    )
    public int deleteAthleteEvent(@Param("athleteId") Long athleteId);
}
