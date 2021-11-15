package com.olympic.athletemanagementsystem.result.service;

import com.olympic.athletemanagementsystem.result.entity.Result;
import com.olympic.athletemanagementsystem.result.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Transactional
    public Result addResult(Result result){
        return resultRepository.save(result);
    }

    public Page<Result> getAllResults(Pageable pageable){
        return resultRepository.findAll(pageable);
    }

    public Result getResultById(Long resultId){
        return resultRepository.findResultByResultId(resultId);
    }

    @Transactional
    public boolean deleteResultById(Long resultId){
        resultRepository.deleteById(resultId);
        return true;
    }

    public int addAthleteResults(Long athleteId, Long resultId){
        return resultRepository.addAthleteResults(athleteId, resultId);
    }
}
