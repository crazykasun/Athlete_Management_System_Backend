package com.olympic.athletemanagementsystem.event.controller;

import com.olympic.athletemanagementsystem.common.util.ObjectInitializer;
import com.olympic.athletemanagementsystem.event.entity.Event;
import com.olympic.athletemanagementsystem.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.olympic.athletemanagementsystem.common.constants.Routes.*;

@CrossOrigin
@RestController
@RequestMapping( value = API_EVENT)
public class EventController {
    public static final Logger log = Logger.getLogger(EventController.class.getName());
    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> saveEvent(@RequestBody Event event){
        try{
            return new ResponseEntity<Object>(eventService.saveEvent(event), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(@RequestParam int page,
                                          @RequestParam int limit,
                                          @RequestParam String sortBy,
                                          @RequestParam String orderBy,
                                          @RequestParam boolean enabled){
        try{
            Pageable pageObj;

            if (orderBy.equals("desc"))
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).descending());
            else
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).ascending());

            return new ResponseEntity<Object>(eventService.getAllEvents(pageObj, enabled), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(API_EVENT_BY_ID)
    public ResponseEntity<?> deleteEventByEventId(@PathVariable Long eventId){
        try{
            if (eventService.getEventById(eventId) == null)
                return new ResponseEntity<Object>("Event not found!", HttpStatus.NOT_FOUND);

            return new ResponseEntity<Object>(eventService.deleteEventById(eventId), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(API_EVENT_BY_ID)
    public ResponseEntity<?> updateEventById(@PathVariable Long eventId, @RequestBody Event event){
        try{
            Event dbEvent = eventService.getEventById(eventId);

            if (dbEvent == null)
                return new ResponseEntity<Object>("Event not found!", HttpStatus.NOT_FOUND);

            ObjectInitializer<Event> init = new ObjectInitializer<>(event, dbEvent);
            dbEvent = init.updateObject();

            return new ResponseEntity<Object>(eventService.saveEvent(dbEvent), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
