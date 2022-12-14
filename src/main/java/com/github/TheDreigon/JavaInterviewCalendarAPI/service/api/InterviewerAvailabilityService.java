package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;

import java.util.List;

/**
 * Common interface for interviewerAvailability services, provides methods to manage interviewerAvailabilities
 */
public interface InterviewerAvailabilityService {

    /**
     * Get all interviewerAvailabilities
     *
     * @return all interviewerAvailabilities
     */
    List<InterviewerAvailability> getInterviewerAvailabilityList();

    /**
     * Retrieves an interviewerAvailability object for the given interviewerAvailability ID
     *
     * @param id the interviewerAvailability ID
     * @return the interviewerAvailability object
     */
    InterviewerAvailability getInterviewerAvailability(Integer id);
}
