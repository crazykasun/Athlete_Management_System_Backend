package com.olympic.athletemanagementsystem.event.controller;

import com.olympic.athletemanagementsystem.common.util.ObjectInitializer;
import com.olympic.athletemanagementsystem.event.entity.EventCategory;
import com.olympic.athletemanagementsystem.event.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.olympic.athletemanagementsystem.common.constants.Routes.*;

@CrossOrigin
@RestController
@RequestMapping( value = API_CATEGORY)
public class EventCategoryController {
    public static final Logger log = Logger.getLogger(EventCategoryController.class.getName());
    @Autowired
    private EventCategoryService eventCategoryService;

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody EventCategory eventCategory){
        try{
            return new ResponseEntity<Object>(eventCategoryService.saveEventCategory(eventCategory), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(@RequestParam int page,
                                          @RequestParam int limit){
        try{
            Pageable pageObj= PageRequest.of(page, limit);
            return new ResponseEntity<Object>(eventCategoryService.getAllCategories(pageObj), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(API_CATEGORY_BY_ID)
    public ResponseEntity<?> deleteCategoryByEventId(@PathVariable Long categoryId){
        try{
            EventCategory eventCategory = eventCategoryService.getCategoryById(categoryId);
            if (eventCategory == null)
                return new ResponseEntity<Object>("Event category not found!", HttpStatus.NOT_FOUND);

            return new ResponseEntity<Object>(eventCategory, HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(API_CATEGORY_BY_ID)
    public ResponseEntity<?> updateCategoryById(@PathVariable Long categoryId, @RequestBody EventCategory eventCategory){
        try{
            EventCategory dbEventCategory = eventCategoryService.getCategoryById(categoryId);

            if (dbEventCategory == null)
                return new ResponseEntity<Object>("Event category not found!", HttpStatus.NOT_FOUND);

            ObjectInitializer<EventCategory> init = new ObjectInitializer<>(eventCategory, dbEventCategory);
            dbEventCategory = init.updateObject();

            return new ResponseEntity<Object>(eventCategoryService.saveEventCategory(dbEventCategory), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
