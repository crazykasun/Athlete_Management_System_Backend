package com.olympic.athletemanagementsystem.result.controller;

import com.olympic.athletemanagementsystem.common.util.ObjectInitializer;
import com.olympic.athletemanagementsystem.result.dto.AthleteResultsDTO;
import com.olympic.athletemanagementsystem.result.entity.Result;
import com.olympic.athletemanagementsystem.result.service.ResultService;
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
@RequestMapping( value = API_RESULT)
public class ResultController {
    public static final Logger log = Logger.getLogger(ResultController.class.getName());
    @Autowired
    private ResultService resultService;

    @PostMapping
    public ResponseEntity<?> saveResult(@RequestBody Result result){
        try{
            return new ResponseEntity<Object>(resultService.addResult(result), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(API_ATHLETE_RESULTS)
    public ResponseEntity<?> saveAthleteResults(@RequestBody AthleteResultsDTO athleteResultsDTO){
        try{
            return new ResponseEntity<Object>(resultService.addAthleteResults(athleteResultsDTO.getAthleteId(),
                    athleteResultsDTO.getResultId()) == 1 ? "Result added successfully": "Error occurred!", HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllResults(@RequestParam int page,
                                          @RequestParam int limit,
                                          @RequestParam String sortBy,
                                          @RequestParam String orderBy){
        try{
            Pageable pageObj;

            if (orderBy.equals("desc"))
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).descending());
            else
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).ascending());

            return new ResponseEntity<Object>(resultService.getAllResults(pageObj), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(API_RESULT_BY_ID)
    public ResponseEntity<?> getResultById(@PathVariable Long resultId){
        try{
            Result result = resultService.getResultById(resultId);

            if (result == null)
                return new ResponseEntity<Object>("Result not found!", HttpStatus.NOT_FOUND);

            return new ResponseEntity<Object>(result, HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(API_RESULT_BY_ID)
    public ResponseEntity<?> deleteResultByEventId(@PathVariable Long resultId){
        try{
            if (resultService.getResultById(resultId) == null)
                return new ResponseEntity<Object>("Result not found!", HttpStatus.NOT_FOUND);

            return new ResponseEntity<Object>(resultService.deleteResultById(resultId), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(API_RESULT_BY_ID)
    public ResponseEntity<?> updateResultById(@PathVariable Long resultId, @RequestBody Result result){
        try{
            Result dbResult = resultService.getResultById(resultId);

            if (dbResult == null)
                return new ResponseEntity<Object>("Result not found!", HttpStatus.NOT_FOUND);

            ObjectInitializer<Result> init = new ObjectInitializer<>(result, dbResult);
            dbResult = init.updateObject();

            return new ResponseEntity<Object>(resultService.addResult(dbResult), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
