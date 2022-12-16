package com.github.TheDreigon.JavaInterviewCalendarAPI.controller.modelController;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
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

        log.info("GetAll Method called");

        List<InterviewerDto> interviewerDtoList = new ArrayList<>();

        try {
            for (Interviewer savedInterviewer : interviewerService.getInterviewerList()) {
                InterviewerDto resultingInterviewerDto = interviewerToInterviewerDto.convert(savedInterviewer);
                interviewerDtoList.add(resultingInterviewerDto);
            }

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

        log.info("Get Method called");

        if (interviewerService.getInterviewer(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Interviewer savedInterviewer = interviewerService.getInterviewer(id);
            InterviewerDto resultingInterviewerDto = interviewerToInterviewerDto.convert(savedInterviewer);

            return new ResponseEntity<>(resultingInterviewerDto, HttpStatus.OK);

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

        log.info("Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                Interviewer savedInterviewer = interviewerService.createInterviewer(interviewerDtoToInterviewer.convert(interviewerDto));
                InterviewerDto resultingInterviewerDto = interviewerToInterviewerDto.convert(savedInterviewer);

                // get help from the framework building the path for the newly created resource
                assert resultingInterviewerDto != null;
                UriComponents uriComponents = uriComponentsBuilder.path("/api/interviewers/" + resultingInterviewerDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(resultingInterviewerDto, headers, HttpStatus.CREATED);

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

        log.info("Put Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else if (interviewerService.getInterviewer(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {

            try {
                interviewerDto.setId(id);

                Interviewer savedInterviewer = interviewerService.updateInterviewer(interviewerDtoToInterviewer.convert(interviewerDto));
                InterviewerDto resultingInterviewerDto = interviewerToInterviewerDto.convert(savedInterviewer);

                return new ResponseEntity<>(resultingInterviewerDto, HttpStatus.OK);

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

        log.info("Delete Method called");

        if (interviewerService.getInterviewer(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            interviewerService.deleteInterviewer(id);

            return new ResponseEntity<>(HttpStatus.OK);

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
    @GetMapping("/")
    public ResponseEntity<List<InterviewerAvailabilityDto>> getInterviewerAvailabilities() {

        return null;
    }

    /**
     * Adds an interviewerAvailability
     *
     * @param interviewerAvailabilityDto  the interviewerAvailability DTO
     * @param bindingResult             the binding result object
     * @param uriComponentsBuilder      the uri components builder
     * @return the added availability
     */
    @PostMapping("/")
    public ResponseEntity<InterviewerAvailabilityDto> addInterviewerAvailability(@Valid @RequestBody InterviewerAvailabilityDto interviewerAvailabilityDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {


        return null;
    }

    /**
     * Deletes an interviewerAvailability
     *
     * @param id the interviewerAvailabilities id
     * @return the http confirmation status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInterviewerAvailability(@PathVariable("id") Integer id) {


        return null;
    }
}
