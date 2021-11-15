package com.olympic.athletemanagementsystem.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCategory {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "event_category_sequence",
            sequenceName = "event_category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_category_sequence"
    )
    private Long categoryId;
    private String type;

    //relationship section
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventCategory")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Event> events;
}
