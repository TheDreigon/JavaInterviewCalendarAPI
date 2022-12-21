package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.CandidateAvailabilityDto;
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

    ////all availabilities for a given user


    /**
     * Retrieves a candidateAvailability object for the given candidate and candidateAvailability ID
     *
     * @param cId  the candidate ID
     * @param caId the candidateAvailability ID
     * @return the candidateAvailability data transfer object
     */
    CandidateAvailabilityDto getCandidateAvailability(Integer cId, Integer caId) throws CandidateNotFoundException, AvailabilityNotFoundException ;

    /**
     * Updates a candidateAvailability object for the given candidate and candidateAvailability ID
     *
     * @param cId                      the candidate ID
     * @param caId                     the candidateAvailability ID
     * @param candidateAvailabilityDto the candidateAvailability data transfer object
     * @return the candidateAvailability data transfer object
     */
    CandidateAvailabilityDto updateCandidateAvailability(Integer cId, Integer caId, CandidateAvailabilityDto candidateAvailabilityDto)
            throws CandidateNotFoundException, AvailabilityNotFoundException;
}
