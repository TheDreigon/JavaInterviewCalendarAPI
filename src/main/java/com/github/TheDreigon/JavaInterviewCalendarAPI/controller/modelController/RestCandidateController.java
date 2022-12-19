package com.github.TheDreigon.JavaInterviewCalendarAPI.controller.modelController;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.candidate.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.candidate.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateAvailabilityService;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateService;
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
 * REST controller responsible for {@link Candidate} related CRUD operations
 */
@Slf4j
@RestController
@RequestMapping("/api/candidates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestCandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateAvailabilityService candidateAvailabilityService;

    /**
     * Retrieves a representation of the list of candidates
     *
     * @return the list of candidates
     */
    @GetMapping("/")
    public ResponseEntity<List<CandidateDto>> getCandidates() {

        log.info("Candidate - GetAll Method called");

        try {
            List<CandidateDto> candidateDtoList = new ArrayList<>(candidateService.getCandidateList());

            return new ResponseEntity<>(candidateDtoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a representation of the given candidate
     *
     * @param id the candidate id
     * @return the asked candidate
     */
    @GetMapping("/{id}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable("id") Integer id) {

        log.info("Candidate - Get Method called");

        try {
            CandidateDto candidateDto = candidateService.getCandidate(id);
            return new ResponseEntity<>(candidateDto, HttpStatus.OK);

        } catch (CandidateNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a candidate
     *
     * @param candidateDto         the candidate DTO
     * @param bindingResult        the binding result object
     * @param uriComponentsBuilder the uri components builder
     * @return the added candidate
     */
    @PostMapping("/")
    public ResponseEntity<CandidateDto> addCandidate(@Valid @RequestBody CandidateDto candidateDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        log.info("Candidate - Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                CandidateDto createdCandidateDto = candidateService.createCandidate(candidateDto);

                // get help from the framework building the path for the newly created resource
                UriComponents uriComponents = uriComponentsBuilder.path("/api/candidates/" + createdCandidateDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(createdCandidateDto, headers, HttpStatus.CREATED);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Edits a candidate
     *
     * @param candidateDto  the candidate DTO
     * @param id            the candidate id
     * @param bindingResult the binding result
     * @return the edited candidate
     */
    @PutMapping("/{id}")
    public ResponseEntity<CandidateDto> editCandidate(@Valid @RequestBody CandidateDto candidateDto, BindingResult bindingResult, @PathVariable("id") Integer id) {

        log.info("Candidate - Put Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                CandidateDto editedCandidateDto = candidateService.updateCandidate(candidateDto, id);

                return new ResponseEntity<>(editedCandidateDto, HttpStatus.OK);

            } catch (CandidateNotFoundException e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Deletes a candidate
     *
     * @param id the candidate id
     * @return the http confirmation status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCandidate(@PathVariable("id") Integer id) {

        log.info("Candidate - Delete Method called");

        try {
            candidateService.deleteCandidate(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (CandidateNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a representation of the given candidateAvailability
     *
     * @param cId the candidate id
     * @param caId the candidateAvailability id
     * @return the asked candidateAvailability
     */
    @GetMapping("/{cId}/availabilities/{caId}")
    public ResponseEntity<CandidateAvailabilityDto> getCandidateAvailabilityById(@PathVariable("cId") Integer cId, @PathVariable("caId") Integer caId) {

        log.info("CandidateAvailability - Get Method called");

        try {
            CandidateAvailabilityDto candidateAvailabilityDto = candidateAvailabilityService.getCandidateAvailability(cId, caId);
            return new ResponseEntity<>(candidateAvailabilityDto, HttpStatus.OK);

        } catch (CandidateNotFoundException | AvailabilityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a candidateAvailability
     *
     * @param cId                      the candidate id
     * @param candidateAvailabilityDto the candidateAvailability DTO
     * @param bindingResult            the binding result object
     * @param uriComponentsBuilder     the uri components builder
     * @return the added availability
     */
    @PostMapping("/{cId}/availabilities/")
    public ResponseEntity<CandidateAvailabilityDto> addCandidateAvailability(@PathVariable("cId") Integer cId, @Valid @RequestBody CandidateAvailabilityDto candidateAvailabilityDto,
                                                                             BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        log.info("CandidateAvailability - Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                CandidateAvailabilityDto createdCandidateAvailabilityDto = candidateService.createCandidateAvailability(cId, candidateAvailabilityDto);

                // get help from the framework building the path for the newly created resource
                UriComponents uriComponents = uriComponentsBuilder.path("/api/candidates/" + cId + "/availabilities/" + createdCandidateAvailabilityDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(createdCandidateAvailabilityDto, headers, HttpStatus.CREATED);

            } catch (CandidateNotFoundException e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Deletes a candidateAvailability
     *
     * @param cId the candidate id
     * @param caId the candidateAvailability id
     * @return the http confirmation status
     */
    @DeleteMapping("/{cId}/availabilities/{caId}")
    public ResponseEntity<HttpStatus> deleteCandidateAvailability(@PathVariable("cId") Integer cId, @PathVariable("caId") Integer caId) {

        log.info("CandidateAvailability - Delete Method called");

        try {
            candidateService.deleteCandidateAvailability(cId, caId);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (CandidateNotFoundException | AvailabilityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
