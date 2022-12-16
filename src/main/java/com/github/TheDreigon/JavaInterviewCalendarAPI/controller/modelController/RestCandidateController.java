package com.github.TheDreigon.JavaInterviewCalendarAPI.controller.modelController;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
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

    /**
     * Retrieves a representation of the list of candidates
     *
     * @return the list of candidates
     */
    @GetMapping("/")
    public ResponseEntity<List<CandidateDto>> getCandidates() {

        log.info("GetAll Method called");

        List<CandidateDto> candidateDtoList = new ArrayList<>();

        try {
            for (Candidate savedCandidate : candidateService.getCandidateList()) {
                CandidateDto resultingCandidateDto = candidateToCandidateDto.convert(savedCandidate);
                candidateDtoList.add(resultingCandidateDto);
            }

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

        log.info("Get Method called");

        if (candidateService.getCandidate(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Candidate savedCandidate = candidateService.getCandidate(id);
            CandidateDto resultingCandidateDto = candidateToCandidateDto.convert(savedCandidate);

            return new ResponseEntity<>(resultingCandidateDto, HttpStatus.OK);

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

        log.info("Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                Candidate savedCandidate = candidateService.createCandidate(candidateDtoToCandidate.convert(candidateDto));
                CandidateDto resultingCandidateDto = candidateToCandidateDto.convert(savedCandidate);

                // get help from the framework building the path for the newly created resource
                assert resultingCandidateDto != null;
                UriComponents uriComponents = uriComponentsBuilder.path("/api/candidates/" + resultingCandidateDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(resultingCandidateDto, headers, HttpStatus.CREATED);

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

        log.info("Put Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else if (candidateService.getCandidate(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {

            try {
                candidateDto.setId(id);

                Candidate savedCandidate = candidateService.updateCandidate(candidateDtoToCandidate.convert(candidateDto));
                CandidateDto resultingCandidateDto = candidateToCandidateDto.convert(savedCandidate);

                return new ResponseEntity<>(resultingCandidateDto, HttpStatus.OK);

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

        log.info("Delete Method called");

        if (candidateService.getCandidate(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            candidateService.deleteCandidate(id);

            return new ResponseEntity<>(HttpStatus.OK);

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
    @GetMapping("/")
    public ResponseEntity<List<CandidateAvailabilityDto>> getCandidateAvailabilities() {

        return null;
    }

    /**
     * Adds a candidateAvailability
     *
     * @param candidateAvailabilityDto the candidateAvailability DTO
     * @param bindingResult            the binding result object
     * @param uriComponentsBuilder     the uri components builder
     * @return the added availability
     */
    @PostMapping("/")
    public ResponseEntity<CandidateAvailabilityDto> addCandidateAvailability(@Valid @RequestBody CandidateAvailabilityDto candidateAvailabilityDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {


        return null;
    }

    /**
     * Deletes a candidateAvailability
     *
     * @param id the candidateAvailabilities id
     * @return the http confirmation status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCandidateAvailability(@PathVariable("id") Integer id) {


        return null;
    }
}
