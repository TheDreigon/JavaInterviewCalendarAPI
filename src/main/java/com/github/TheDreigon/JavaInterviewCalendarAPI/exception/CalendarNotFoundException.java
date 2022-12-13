package com.github.TheDreigon.JavaInterviewCalendarAPI.exception;

import com.github.TheDreigon.JavaInterviewCalendarAPI.error.ErrorMessage;

/**
 * Thrown to indicate that the calendar was not found
 */
public class CalendarNotFoundException extends JavaInterviewCalendarAPIException {

    public CalendarNotFoundException() {
        super(ErrorMessage.CALENDAR_NOT_FOUND);
    }
}
