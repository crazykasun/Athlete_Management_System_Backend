package com.olympic.athletemanagementsystem.event.service;

import com.olympic.athletemanagementsystem.event.entity.Event;
import com.olympic.athletemanagementsystem.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }

    @Transactional
    public boolean deleteEventById(Long eventId){
        eventRepository.deleteById(eventId);
        return true;
    }

    public Event getEventById(Long eventId){
        return eventRepository.findEventByEventId(eventId);
    }

    public Page<Event> getAllEvents(Pageable pageable, boolean enabled){
        return eventRepository.findEventByEnabled(pageable, enabled);
    }
}
