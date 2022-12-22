package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.candidate.CandidateDto;
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
     * Retrieves a candidate data transfer object for the given candidate id
     *
     * @param cId the candidate id
     * @return the candidate data transfer object
     */
    CandidateDto getCandidate(Integer cId) throws CandidateNotFoundException;

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
     * @param cId           the candidate id
     * @param candidateDto  the candidate data transfer object
     * @return the updated candidate data transfer object
     */
    CandidateDto updateCandidate(Integer cId, CandidateDto candidateDto) throws CandidateNotFoundException;

    /**
     * Deletes a candidate object for the given candidate id
     *
     * @param cId the candidate id
     */
    void deleteCandidate(Integer cId) throws CandidateNotFoundException;

    /**
     * Get all candidateAvailabilities for the given candidate id
     *
     * @param cId  the candidate id
     * @return the candidateAvailability data transfer objects
     */
    List<CandidateAvailabilityDto> getCandidateAvailabilities(Integer cId) throws CandidateNotFoundException;

    /**
     * Creates a candidateAvailability object
     *
     * @param cId                      the candidate id
     * @param candidateAvailabilityDto the candidateAvailability data transfer object
     * @return the created candidateAvailability data transfer object
     */
    CandidateAvailabilityDto createCandidateAvailability(Integer cId, CandidateAvailabilityDto candidateAvailabilityDto) throws CandidateNotFoundException;

    /**
     * Deletes a candidateAvailability object for the given candidate and candidateAvailability id
     *
     * @param cId  the candidate id
     * @param caId the candidateAvailability id
     */
    void deleteCandidateAvailability(Integer cId, Integer caId) throws AvailabilityNotFoundException, CandidateNotFoundException;
}
