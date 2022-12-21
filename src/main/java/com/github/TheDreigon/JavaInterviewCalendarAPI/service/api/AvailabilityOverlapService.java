package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;

import java.util.List;

/**
 * Common interface for availability overlap services
 */
public interface AvailabilityOverlapService {

    /**
     * Retrieves a list of all overlapping candidate and interviewer availabilities
     *
     * @return the data transfer object list of all candidate and interviewer overlapping availabilities
     */
    List<AvailabilityDto> getAllOverlappingAvailabilities();

    /**
     * Retrieves a list of overlapping candidate and interviewer availabilities for the given ids
     *
     * @param cId  the candidate id
     * @param iId  the interviewer id
     * @return the data transfer object list of overlapping availabilities for the given ids
     */
    List<AvailabilityDto> getCandidateInterviewerOverlappingAvailabilities(Integer cId, Integer iId) throws CandidateNotFoundException, InterviewerNotFoundException;
}
