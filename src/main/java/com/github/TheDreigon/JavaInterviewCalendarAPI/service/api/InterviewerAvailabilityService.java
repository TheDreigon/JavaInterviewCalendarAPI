package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;

import java.util.List;

/**
 * Common interface for interviewerAvailability services, provides methods to manage interviewerAvailabilities
 */
public interface InterviewerAvailabilityService {

    /**
     * Get all interviewerAvailabilities
     *
     * @return all interviewerAvailability data transfer objects
     */
    List<InterviewerAvailabilityDto> getInterviewerAvailabilityList();

    /**
     * Retrieves an interviewerAvailability object for the given interviewerAvailability ID
     *
     * @param id the interviewerAvailability ID
     * @return the interviewerAvailability data transfer object
     */
    InterviewerAvailabilityDto getInterviewerAvailability(Integer id);
}
