package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityInterviewerMismatchException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;

import java.util.List;

/**
 * Common interface for interviewer services, provides methods to manage interviewers
 */
public interface InterviewerService {

    /**
     * Get all interviewers
     *
     * @return all interviewer data transfer objects
     */
    List<InterviewerDto> getInterviewerList();

    /**
     * Retrieves an interviewer data transfer object for the given interviewer id
     *
     * @param iId interviewer id
     * @return the interviewer data transfer object
     */
    InterviewerDto getInterviewer(Integer iId) throws InterviewerNotFoundException;

    /**
     * Creates an interviewer object
     *
     * @param interviewerDto the interviewer data transfer object
     * @return the created interviewer data transfer object
     */
    InterviewerDto createInterviewer(InterviewerDto interviewerDto);

    /**
     * Updates an interviewer object
     *
     * @param iId             the interviewer id
     * @param interviewerDto  the interviewer data transfer object
     * @return the updated interviewer data transfer object
     */
    InterviewerDto updateInterviewer(Integer iId, InterviewerDto interviewerDto) throws InterviewerNotFoundException;

    /**
     * Deletes an interviewer object for the given interviewer id
     *
     * @param iId the interviewer id
     */
    void deleteInterviewer(Integer iId) throws InterviewerNotFoundException;

    /**
     * Get all interviewerAvailabilities for the given interviewer id
     *
     * @param iId  the interviewer id
     * @return the interviewerAvailability data transfer objects
     */
    List<InterviewerAvailabilityDto> getInterviewerAvailabilities(Integer iId) throws InterviewerNotFoundException;

    /**
     * Creates an interviewerAvailability object
     *
     * @param iId                        the interviewer id
     * @param interviewerAvailabilityDto the interviewerAvailability data transfer object
     * @return the created interviewerAvailability data transfer object
     */
    InterviewerAvailabilityDto createInterviewerAvailability(Integer iId, InterviewerAvailabilityDto interviewerAvailabilityDto) throws InterviewerNotFoundException;

    /**
     * Deletes an interviewerAvailability object for the given interviewer and interviewerAvailability id
     *
     * @param iId  the interviewer id
     * @param iaId the interviewerAvailability id
     */
    void deleteInterviewerAvailability(Integer iId, Integer iaId) throws InterviewerNotFoundException, AvailabilityNotFoundException, AvailabilityInterviewerMismatchException;
}
