package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;

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
    CandidateDto getCandidate(Integer id);

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
     * @return the updated candidate data transfer object
     */
    CandidateDto updateCandidate(CandidateDto candidateDto);

    /**
     * Deletes a candidate object for the given candidate ID
     *
     * @param id the candidate ID
     */
    void deleteCandidate(Integer id);

    /**
     * Creates a candidateAvailability object
     *
     * @param candidateAvailabilityDto the candidateAvailability data transfer object
     * @return the created candidateAvailability data transfer object
     */
    CandidateAvailabilityDto createCandidateAvailability(CandidateAvailabilityDto candidateAvailabilityDto);

    /**
     * Deletes a candidateAvailability object for the given candidateAvailability ID
     *
     * @param id the candidateAvailability ID
     */
    void deleteCandidateAvailability(Integer id);
}
