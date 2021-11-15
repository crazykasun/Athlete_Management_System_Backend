package com.olympic.athletemanagementsystem.event.repository;

import com.olympic.athletemanagementsystem.event.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    public Event findEventByEventId(Long eventId);
    public Page<Event> findEventByEnabled(Pageable pageable, boolean enabled);
}
