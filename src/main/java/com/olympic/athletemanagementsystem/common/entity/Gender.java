package com.olympic.athletemanagementsystem.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gender {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "gender_sequence",
            sequenceName = "gender_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gender_sequence"
    )
    private Long id;
    @NotNull(message = "Gender type may not be null")
    private String type;

    //relationship section
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Athlete> athletes;
}
