package com.olympic.athletemanagementsystem.athlete.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.olympic.athletemanagementsystem.common.entity.Gender;
import com.olympic.athletemanagementsystem.event.entity.Event;
import com.olympic.athletemanagementsystem.result.entity.Result;
import lombok.*;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Athlete {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "athlete_sequence",
            sequenceName = "athlete_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "athlete_sequence"
    )
    private Long athleteId;
    @NotNull(message = "First Name may not be null")
    private String firstName;
    private String lastName;
    private String country;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    private Date dob;

    //relationship section
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "athlete_events",
            joinColumns = @JoinColumn(
                    name = "athlete_id",
                    referencedColumnName = "athleteId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id",
                    referencedColumnName = "eventId"
            )
    )
    private List<Event> events;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "athlete_results",
            joinColumns = @JoinColumn(
                    name = "athlete_id",
                    referencedColumnName = "athleteId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "result_id",
                    referencedColumnName = "resultId"
            )
    )
    private List<Result> results;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    private Gender gender;
}
