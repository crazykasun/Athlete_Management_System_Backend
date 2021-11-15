package com.olympic.athletemanagementsystem.event.repository;

import com.olympic.athletemanagementsystem.event.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
    public EventCategory findEventCategoryByCategoryId(Long categoryId);
}
