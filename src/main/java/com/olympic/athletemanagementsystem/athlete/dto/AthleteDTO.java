package com.olympic.athletemanagementsystem.athlete.dto;

import com.olympic.athletemanagementsystem.common.entity.Gender;
import com.olympic.athletemanagementsystem.result.entity.Result;

import java.util.Date;
import java.util.List;

public interface AthleteDTO {
    public Long getAthleteId();
    public String getFirstName();
    public String getLastName();
    public String getCountry();
    public Date getDob();
    public List<Result> getResults();
    public Gender getGender();
}
