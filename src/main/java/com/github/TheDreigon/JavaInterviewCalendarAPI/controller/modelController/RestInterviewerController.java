package com.github.TheDreigon.JavaInterviewCalendarAPI.controller.modelController;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
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
 * REST controller responsible for {@link Interviewer} related CRUD operations
 */
@Slf4j
@RestController
@RequestMapping("/api/interviewers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestInterviewerController {

    @Autowired
    private InterviewerService interviewerService;

    /**
     * Retrieves a representation of the list of interviewers
     *
     * @return the list of interviewers
     */
    @GetMapping("/")
    public ResponseEntity<List<InterviewerDto>> getInterviewers() {

        log.info("Interviewer - GetAll Method called");

        try {
            List<InterviewerDto> interviewerDtoList = new ArrayList<>(interviewerService.getInterviewerList());

            return new ResponseEntity<>(interviewerDtoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a representation of the given interviewer
     *
     * @param id the interviewer id
     * @return the asked interviewer
     */
    @GetMapping("/{id}")
    public ResponseEntity<InterviewerDto> getInterviewerById(@PathVariable("id") Integer id) {

        log.info("Interviewer - Get Method called");

        try {
            InterviewerDto interviewerDto = interviewerService.getInterviewer(id);
            return new ResponseEntity<>(interviewerDto, HttpStatus.OK);

        } catch (InterviewerNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds an interviewer
     *
     * @param interviewerDto       the interviewer DTO
     * @param bindingResult        the binding result object
     * @param uriComponentsBuilder the uri components builder
     * @return the added interviewer
     */
    @PostMapping("/")
    public ResponseEntity<InterviewerDto> addInterviewer(@Valid @RequestBody InterviewerDto interviewerDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        log.info("Interviewer - Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                InterviewerDto createdInterviewerDto = interviewerService.createInterviewer(interviewerDto);

                // get help from the framework building the path for the newly created resource
                UriComponents uriComponents = uriComponentsBuilder.path("/api/interviewers/" + createdInterviewerDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(createdInterviewerDto, headers, HttpStatus.CREATED);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Edits an interviewer
     *
     * @param interviewerDto the interviewer DTO
     * @param id             the interviewer id
     * @param bindingResult  the binding result
     * @return the edited interviewer
     */
    @PutMapping("/{id}")
    public ResponseEntity<InterviewerDto> editInterviewer(@Valid @RequestBody InterviewerDto interviewerDto, BindingResult bindingResult, @PathVariable("id") Integer id) {

        log.info("Interviewer - Put Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                InterviewerDto editedInterviewerDto = interviewerService.updateInterviewer(interviewerDto, id);

                return new ResponseEntity<>(editedInterviewerDto, HttpStatus.OK);

            } catch (InterviewerNotFoundException e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Deletes an interviewer
     *
     * @param id the interviewer id
     * @return the http confirmation status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInterviewer(@PathVariable("id") Integer id) {

        log.info("Interviewer - Delete Method called");

        try {
            interviewerService.deleteInterviewer(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (InterviewerNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds an interviewerAvailability
     *
     * @param interviewerAvailabilityDto  the interviewerAvailability DTO
     * @param bindingResult             the binding result object
     * @param uriComponentsBuilder      the uri components builder
     * @return the added availability
     */
    @PostMapping("/{iId}/availabilities/")
    public ResponseEntity<InterviewerAvailabilityDto> addInterviewerAvailability(@PathVariable("iId") Integer iId, @Valid @RequestBody InterviewerAvailabilityDto interviewerAvailabilityDto,
                                                                                 BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        log.info("InterviewerAvailability - Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                InterviewerAvailabilityDto createdInterviewerAvailabilityDto = interviewerService.createInterviewerAvailability(interviewerAvailabilityDto);

                // get help from the framework building the path for the newly created resource
                UriComponents uriComponents = uriComponentsBuilder.path("/api/candidates/" + iId + "/availabilities/" + createdInterviewerAvailabilityDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(createdInterviewerAvailabilityDto, headers, HttpStatus.CREATED);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Deletes an interviewerAvailability
     *
     * @param iId the interviewer id
     * @param iaId the interviewerAvailability id
     * @return the http confirmation status
     */
    @DeleteMapping("/{iId}/availabilities/{iaId}")
    public ResponseEntity<HttpStatus> deleteInterviewerAvailability(@PathVariable("iId") Integer iId, @PathVariable("iaId") Integer iaId) {

        log.info("InterviewerAvailability - Delete Method called");

        try {
            interviewerService.deleteInterviewerAvailability(iId, iaId);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (AvailabilityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
