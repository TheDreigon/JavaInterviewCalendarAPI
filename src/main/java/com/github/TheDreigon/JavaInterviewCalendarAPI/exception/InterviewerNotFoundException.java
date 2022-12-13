package com.github.TheDreigon.JavaInterviewCalendarAPI.exception;

import com.github.TheDreigon.JavaInterviewCalendarAPI.error.ErrorMessage;

/**
 * Thrown to indicate that the interviewer was not found
 */
public class InterviewerNotFoundException extends JavaInterviewCalendarAPIException {

    public InterviewerNotFoundException() {
        super(ErrorMessage.INTERVIEWER_NOT_FOUND);
    }
}
