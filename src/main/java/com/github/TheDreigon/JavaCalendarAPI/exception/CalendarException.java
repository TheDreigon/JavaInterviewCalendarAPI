package com.github.TheDreigon.JavaCalendarAPI.exception;

/**
 * A generic calendar exception to be used as base for concrete types of exceptions
 *
 * @see Exception
 */
public class CalendarException extends Exception {

    public CalendarException(String message) {
        super(message);
    }
}
