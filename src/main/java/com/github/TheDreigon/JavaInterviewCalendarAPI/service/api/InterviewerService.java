package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;

import java.util.List;

/**
 * Common interface for interviewer services, provides methods to manage interviewers
 */
public interface InterviewerService {

    /**
     * Get all interviewers
     *
     * @return all interviewers
     */
    List<Interviewer> getInterviewerList();

    /**
     * Retrieves an interviewer object for the given interviewer ID
     *
     * @param id the interviewer ID
     * @return the interviewer object
     */
    Interviewer getInterviewer(Integer id);

    /**
     * Creates an interviewer object
     *
     * @param interviewer the interviewer object
     * @return the created interviewer object
     */
    Interviewer createInterviewer(Interviewer interviewer);

    /**
     * Updates an interviewer object
     *
     * @param interviewer the interviewer object
     * @return the updated interviewer object
     */
    Interviewer updateInterviewer(Interviewer interviewer);

    /**
     * Deletes an interviewer object for the given interviewer ID
     *
     * @param id the interviewer ID
     */
    void deleteInterviewer(Integer id);

    /**
     * Creates an interviewerAvailability object
     *
     * @param interviewerAvailability the interviewerAvailability object
     * @return the created interviewerAvailability object
     */
    InterviewerAvailability createInterviewerAvailability(InterviewerAvailability interviewerAvailability);

    /**
     * Deletes an interviewerAvailability object for the given interviewerAvailability ID
     *
     * @param id the interviewerAvailability ID
     */
    void deleteInterviewerAvailability(Integer id);
}
