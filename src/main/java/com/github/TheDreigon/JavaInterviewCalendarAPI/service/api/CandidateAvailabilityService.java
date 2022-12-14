package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;

import java.util.List;

/**
 * Common interface for candidateAvailability services, provides methods to manage candidateAvailabilities
 */
public interface CandidateAvailabilityService {

    /**
     * Get all candidateAvailabilities
     *
     * @return all candidateAvailabilities
     */
    List<CandidateAvailability> getCandidateAvailabilityList();

    /**
     * Retrieves a candidateAvailability object for the given candidateAvailability ID
     *
     * @param id the candidateAvailability ID
     * @return the candidateAvailability object
     */
    CandidateAvailability getCandidateAvailability(Integer id);
}
