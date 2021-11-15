package com.olympic.athletemanagementsystem.common.service;

import com.olympic.athletemanagementsystem.common.entity.Gender;
import com.olympic.athletemanagementsystem.common.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenderService {
    @Autowired
    private GenderRepository genderRepository;

    @Transactional
    public Gender saveGender(Gender gender) {
        return genderRepository.save(gender);
    }

    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    public Gender getGenderById(Long genderId) {
        return genderRepository.findGenderById(genderId);
    }

    @Transactional
    public boolean deleteGenderById(Long id){
        genderRepository.deleteById(id);
        return true;
    }
}
