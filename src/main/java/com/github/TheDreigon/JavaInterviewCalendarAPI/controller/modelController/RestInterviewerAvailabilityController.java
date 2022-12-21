package com.github.TheDreigon.JavaInterviewCalendarAPI.controller.modelController;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerAvailabilityService;
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
 * REST controller responsible for {@link InterviewerAvailability} related CRUD operations
 */
@Slf4j
@RestController
@RequestMapping("/api/interviewers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestInterviewerAvailabilityController {

    @Autowired
    private InterviewerService interviewerService;

    @Autowired
    private InterviewerAvailabilityService interviewerAvailabilityService;

    /**
     * Retrieves a representation of the list of interviewerAvailabilities
     *
     * @return the list of interviewerAvailabilities
     */
    @GetMapping("/availabilities/")
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

    /**
     * Retrieves a representation of the given interviewerAvailability
     *
     * @param iId the interviewer id
     * @param iaId the interviewerAvailability id
     * @return the asked interviewerAvailability
     */
    @GetMapping("/{cId}/availabilities/{caId}")
    public ResponseEntity<InterviewerAvailabilityDto> getInterviewerAvailabilityById(@PathVariable("iId") Integer iId, @PathVariable("iaId") Integer iaId) {

        log.info("CandidateAvailability - Get Method called");

        try {
            InterviewerAvailabilityDto interviewerAvailabilityDto = interviewerAvailabilityService.getInterviewerAvailability(iId, iaId);
            return new ResponseEntity<>(interviewerAvailabilityDto, HttpStatus.OK);

        } catch (InterviewerNotFoundException | AvailabilityNotFoundException e) {
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
     * @return the added interviewerAvailability
     */
    @PostMapping("/{iId}/availabilities/")
    public ResponseEntity<InterviewerAvailabilityDto> addInterviewerAvailability(@PathVariable("iId") Integer iId, @Valid @RequestBody InterviewerAvailabilityDto interviewerAvailabilityDto,
                                                                                 BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        log.info("InterviewerAvailability - Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                InterviewerAvailabilityDto createdInterviewerAvailabilityDto = interviewerService.createInterviewerAvailability(iId, interviewerAvailabilityDto);

                // get help from the framework building the path for the newly created resource
                UriComponents uriComponents = uriComponentsBuilder.path("/api/candidates/" + iId + "/availabilities/" + createdInterviewerAvailabilityDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(createdInterviewerAvailabilityDto, headers, HttpStatus.CREATED);

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

        } catch (InterviewerNotFoundException | AvailabilityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
