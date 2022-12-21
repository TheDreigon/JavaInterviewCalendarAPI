package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityCandidateMismatchException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;

import java.util.List;

/**
 * Common interface for candidateAvailability services, provides methods to manage candidateAvailabilities
 */
public interface CandidateAvailabilityService {

    /**
     * Get all candidateAvailabilities
     *
     * @return all candidateAvailability data transfer objects
     */
    List<CandidateAvailabilityDto> getCandidateAvailabilityList();

    /**
     * Retrieves a candidateAvailability object for the given candidate and candidateAvailability id
     *
     * @param cId  the candidate id
     * @param caId the candidateAvailability id
     * @return the candidateAvailability data transfer object
     */
    CandidateAvailabilityDto getCandidateAvailability(Integer cId, Integer caId) throws CandidateNotFoundException, AvailabilityNotFoundException, AvailabilityCandidateMismatchException;

    /**
     * Updates a candidateAvailability object for the given candidate and candidateAvailability id
     *
     * @param cId                      the candidate id
     * @param caId                     the candidateAvailability id
     * @param candidateAvailabilityDto the candidateAvailability data transfer object
     * @return the candidateAvailability data transfer object
     */
    CandidateAvailabilityDto updateCandidateAvailability(Integer cId, Integer caId, CandidateAvailabilityDto candidateAvailabilityDto)
            throws CandidateNotFoundException, AvailabilityNotFoundException, AvailabilityCandidateMismatchException;
}
