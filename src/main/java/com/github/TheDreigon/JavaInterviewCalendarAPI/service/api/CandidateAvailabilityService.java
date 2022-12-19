package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.candidate.CandidateAvailabilityDto;
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
     * Retrieves a candidateAvailability object for the given candidate and candidateAvailability ID
     *
     * @param cId the candidate ID
     * @param caId the candidateAvailability ID
     * @return the candidateAvailability data transfer object
     */
    CandidateAvailabilityDto getCandidateAvailability(Integer cId, Integer caId) throws CandidateNotFoundException, AvailabilityNotFoundException ;
}
