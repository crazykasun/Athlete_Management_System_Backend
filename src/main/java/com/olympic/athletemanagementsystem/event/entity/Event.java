package com.olympic.athletemanagementsystem.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import com.olympic.athletemanagementsystem.result.entity.Result;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long eventId;
    private String name;
    private String type;
    private Date date;
    private Time time;
    private String location;
    private boolean enabled;

    //relationship section
    @JsonIgnore
    @ManyToMany(mappedBy = "events",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Athlete> athletes;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    private EventCategory eventCategory;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<Result> results;
}
