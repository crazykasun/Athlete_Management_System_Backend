package com.olympic.athletemanagementsystem.common.repository;

import com.olympic.athletemanagementsystem.common.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    public Gender findGenderById(Long id);
}
