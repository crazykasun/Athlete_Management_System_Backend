package com.olympic.athletemanagementsystem.event.service;

import com.olympic.athletemanagementsystem.event.entity.EventCategory;
import com.olympic.athletemanagementsystem.event.repository.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EventCategoryService {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Transactional
    public EventCategory saveEventCategory(EventCategory eventCategory){
        return eventCategoryRepository.save(eventCategory);
    }

    public EventCategory getCategoryById(Long categoryId){
        return eventCategoryRepository.findEventCategoryByCategoryId(categoryId);
    }

    public Page<EventCategory> getAllCategories(Pageable pageable){
        return eventCategoryRepository.findAll(pageable);
    }

    @Transactional
    public boolean deleteCategoryById(Long categoryId){
        eventCategoryRepository.deleteById(categoryId);
        return true;
    }
}
