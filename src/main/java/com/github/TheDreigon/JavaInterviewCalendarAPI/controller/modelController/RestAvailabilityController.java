package com.github.TheDreigon.JavaInterviewCalendarAPI.controller.modelController;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.CandidateAvailabilityDtoToCandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.CandidateAvailabilityToCandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerAvailabilityDtoToInterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerAvailabilityToInterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.abstractModel.AbstractAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateService;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
    private CandidateService candidateService;

    @Autowired
    private InterviewerService interviewerService;

    @Autowired
    private CandidateAvailabilityToCandidateAvailabilityDto candidateAvailabilityToCandidateAvailabilityDto;

    @Autowired
    private CandidateAvailabilityDtoToCandidateAvailability candidateAvailabilityDtoToCandidateAvailability;

    @Autowired
    private InterviewerAvailabilityToInterviewerAvailabilityDto interviewerAvailabilityToInterviewerAvailabilityDto;

    @Autowired
    private InterviewerAvailabilityDtoToInterviewerAvailability interviewerAvailabilityDtoToInterviewerAvailability;

    /**
     * Retrieves a representation of the list of all candidate and interviewer availabilities
     *
     * @return the list of availabilities
     */
    @GetMapping("/")
    public ResponseEntity<List<? extends AbstractAvailability>> getAllAvailabilities() {

        log.info("Availability - GetAll Method called - Candidates and Interviewers");


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
            List<CandidateDto> candidateDtoList = new ArrayList<>(candidateService.getCandidateList());

            return new ResponseEntity<>(candidateDtoList, HttpStatus.OK);

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
            List<InterviewerDto> interviewerDtoList = new ArrayList<>(interviewerService.getInterviewerList());

            return new ResponseEntity<>(interviewerDtoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
