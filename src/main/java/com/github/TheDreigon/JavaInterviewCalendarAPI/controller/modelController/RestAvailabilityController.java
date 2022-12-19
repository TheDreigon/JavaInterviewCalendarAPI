package com.github.TheDreigon.JavaInterviewCalendarAPI.controller.modelController;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.abstractModel.AbstractAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateAvailabilityService;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerAvailabilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * REST controller responsible for {@link AbstractAvailability} related CRUD operations
 */
@Slf4j
@RestController
@RequestMapping("/api/availabilities")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestAvailabilityController {

    @Autowired
    private CandidateAvailabilityService candidateAvailabilityService;

    @Autowired
    private InterviewerAvailabilityService interviewerAvailabilityService;

    /**
     * Retrieves a representation of the list of all candidate and interviewer availabilities
     *
     * @return the list of availabilities
     */
    @GetMapping("/")
    public ResponseEntity<List<? extends AvailabilityDto>> getAllAvailabilities() {

        log.info("Availability - GetAll Method called - Candidates and Interviewers");

        try {
            List<CandidateAvailabilityDto> candidateAvailabilityDtoList = new ArrayList<>(candidateAvailabilityService.getCandidateAvailabilityList());
            List<InterviewerAvailabilityDto> interviewerAvailabilityDtoList = new ArrayList<>(interviewerAvailabilityService.getInterviewerAvailabilityList());

            List<? extends AvailabilityDto> availabilityDtoList = Stream.concat(candidateAvailabilityDtoList.stream(), interviewerAvailabilityDtoList.stream()).toList();

            return new ResponseEntity<>(availabilityDtoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a representation of the list of candidateAvailabilities
     *
     * @return the list of candidateAvailabilities
     */
    @GetMapping("/candidates/")
    public ResponseEntity<List<CandidateAvailabilityDto>> getCandidateAvailabilities() {

        log.info("CandidateAvailability - GetAll Method called");

        try {
            List<CandidateAvailabilityDto> candidateAvailabilityDtoList = new ArrayList<>(candidateAvailabilityService.getCandidateAvailabilityList());

            return new ResponseEntity<>(candidateAvailabilityDtoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a representation of the list of interviewerAvailabilities
     *
     * @return the list of interviewerAvailabilities
     */
    @GetMapping("/interviewers/")
    public ResponseEntity<List<InterviewerAvailabilityDto>> getInterviewerAvailabilities() {

        log.info("InterviewerAvailability - GetAll Method called");

        try {
            List<InterviewerAvailabilityDto> interviewerAvailabilityDtoList = new ArrayList<>(interviewerAvailabilityService.getInterviewerAvailabilityList());

            return new ResponseEntity<>(interviewerAvailabilityDtoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
