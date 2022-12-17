package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;

import java.util.List;

/**
 * Common interface for candidate services, provides methods to manage candidates
 */
public interface CandidateService {

    /**
     * Get all candidates
     *
     * @return all candidate data transfer objects
     */
    List<CandidateDto> getCandidateList();

    /**
     * Retrieves a candidate data transfer object for the given candidate ID
     *
     * @param id the candidate ID
     * @return the candidate data transfer object
     */
    CandidateDto getCandidate(Integer id) throws CandidateNotFoundException;

    /**
     * Creates a candidate object
     *
     * @param candidateDto the candidate data transfer object
     * @return the created candidate data transfer object
     */
    CandidateDto createCandidate(CandidateDto candidateDto);

    /**
     * Updates a candidate object
     *
     * @param candidateDto the candidate data transfer object
     * @param id the candidate ID
     * @return the updated candidate data transfer object
     */
    CandidateDto updateCandidate(CandidateDto candidateDto, Integer id) throws CandidateNotFoundException;

    /**
     * Deletes a candidate object for the given candidate ID
     *
     * @param id the candidate ID
     */
    void deleteCandidate(Integer id) throws CandidateNotFoundException;

    /**
     * Creates a candidateAvailability object
     *
     * @param candidateAvailabilityDto the candidateAvailability data transfer object
     * @return the created candidateAvailability data transfer object
     */
    CandidateAvailabilityDto createCandidateAvailability(CandidateAvailabilityDto candidateAvailabilityDto);

    /**
     * Deletes a candidateAvailability object for the given candidate and candidateAvailability ID
     *
     * @param cId   the candidate ID
     * @param caId  the candidateAvailability ID
     */
    void deleteCandidateAvailability(Integer cId, Integer caId) throws AvailabilityNotFoundException, CandidateNotFoundException;
}
