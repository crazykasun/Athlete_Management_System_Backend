package com.olympic.athletemanagementsystem.athlete.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AthleteEventDTO {
    private Long athleteId;
    private Long eventId;
}
