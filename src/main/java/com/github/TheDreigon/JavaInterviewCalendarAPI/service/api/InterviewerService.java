package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerDto;
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
     * Retrieves an interviewer data transfer object for the given interviewer ID
     *
     * @param id the interviewer ID
     * @return the interviewer data transfer object
     */
    InterviewerDto getInterviewer(Integer id) throws InterviewerNotFoundException;

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
     * @param interviewerDto the interviewer data transfer object
     * @param id             the interviewer ID
     * @return the updated interviewer data transfer object
     */
    InterviewerDto updateInterviewer(InterviewerDto interviewerDto, Integer id) throws InterviewerNotFoundException;

    /**
     * Deletes an interviewer object for the given interviewer ID
     *
     * @param id the interviewer ID
     */
    void deleteInterviewer(Integer id) throws InterviewerNotFoundException;

    /**
     * Creates an interviewerAvailability object
     *
     * @param iId                        the interviewer ID
     * @param interviewerAvailabilityDto the interviewerAvailability data transfer object
     * @return the created interviewerAvailability data transfer object
     */
    InterviewerAvailabilityDto createInterviewerAvailability(Integer iId, InterviewerAvailabilityDto interviewerAvailabilityDto) throws InterviewerNotFoundException;

    /**
     * Deletes an interviewerAvailability object for the given interviewer and interviewerAvailability ID
     *
     * @param iId   the interviewer ID
     * @param iaId  the interviewerAvailability ID
     */
    void deleteInterviewerAvailability(Integer iId, Integer iaId) throws InterviewerNotFoundException, AvailabilityNotFoundException;
}
