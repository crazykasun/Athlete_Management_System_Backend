package com.olympic.athletemanagementsystem.result.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import com.olympic.athletemanagementsystem.event.entity.Event;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "result_sequence",
            sequenceName = "result_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "result_sequence"
    )
    private Long resultId;
    private String round;
    private String place;

    //relationship section
    @JsonIgnore
    @ManyToMany(mappedBy = "results",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Athlete> athletes;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    private Event event;
}
