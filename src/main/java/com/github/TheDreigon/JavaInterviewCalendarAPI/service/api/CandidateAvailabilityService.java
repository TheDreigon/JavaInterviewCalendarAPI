package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;

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
     * Retrieves a candidateAvailability object for the given candidateAvailability ID
     *
     * @param id the candidateAvailability ID
     * @return the candidateAvailability data transfer object
     */
    CandidateAvailabilityDto getCandidateAvailability(Integer id);
}
