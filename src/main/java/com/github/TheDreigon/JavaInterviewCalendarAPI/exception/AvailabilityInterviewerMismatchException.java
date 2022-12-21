package com.github.TheDreigon.JavaInterviewCalendarAPI.exception;

import com.github.TheDreigon.JavaInterviewCalendarAPI.error.ErrorMessage;

/**
 * Thrown to indicate that the availability was not found for the asked interviewer
 */
public class AvailabilityInterviewerMismatchException extends JavaInterviewCalendarAPIException {

    public AvailabilityInterviewerMismatchException() {
        super(ErrorMessage.AVAILABILITY_NOT_AVAILABLE_FOR_GIVEN_INTERVIEWER);
    }
}
