package com.github.TheDreigon.JavaInterviewCalendarAPI.exception;

import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.error.ErrorMessage;

/**
 * Thrown to indicate that the candidate was not found
 */
public class CandidateNotFoundException extends JavaInterviewCalendarAPIException {

    public CandidateNotFoundException() {
        super(ErrorMessage.CANDIDATE_NOT_FOUND);
    }
}
