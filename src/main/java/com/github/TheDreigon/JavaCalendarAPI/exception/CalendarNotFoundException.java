package com.github.TheDreigon.JavaCalendarAPI.exception;

import com.github.TheDreigon.JavaCalendarAPI.error.ErrorMessage;

/**
 * Thrown to indicate that the calendar was not found
 */
public class CalendarNotFoundException extends CalendarException {

    public CalendarNotFoundException() {
        super(ErrorMessage.CALENDAR_NOT_FOUND);
    }
}
