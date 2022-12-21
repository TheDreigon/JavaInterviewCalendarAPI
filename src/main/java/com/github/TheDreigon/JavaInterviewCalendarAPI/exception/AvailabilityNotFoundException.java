package com.github.TheDreigon.JavaInterviewCalendarAPI.exception;

import com.github.TheDreigon.JavaInterviewCalendarAPI.error.ErrorMessage;

/**
 * Thrown to indicate that the availability was not found
 */
public class AvailabilityNotFoundException extends JavaInterviewCalendarAPIException {

    public AvailabilityNotFoundException() {
        super(ErrorMessage.AVAILABILITY_NOT_FOUND);
    }
}
