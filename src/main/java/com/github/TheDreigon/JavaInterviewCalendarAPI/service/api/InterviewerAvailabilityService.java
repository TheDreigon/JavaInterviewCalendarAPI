package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;

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
     * Retrieves an interviewerAvailability object for the given interviewerAvailability id
     *
     * @param iId  the interviewer id
     * @param iaId the interviewerAvailability id
     * @return the interviewerAvailability data transfer object
     */
    InterviewerAvailabilityDto getInterviewerAvailability(Integer iId, Integer iaId) throws InterviewerNotFoundException, AvailabilityNotFoundException;

    /**
     * Updates an interviewerAvailability object for the given candidate and interviewerAvailability id
     *
     * @param iId                        the interviewer id
     * @param iaId                       the interviewerAvailability id
     * @param interviewerAvailabilityDto the interviewerAvailability data transfer object
     * @return the interviewerAvailability data transfer object
     */
    InterviewerAvailabilityDto updateInterviewerAvailability(Integer iId, Integer iaId, InterviewerAvailabilityDto interviewerAvailabilityDto)
            throws InterviewerNotFoundException, AvailabilityNotFoundException;
}
