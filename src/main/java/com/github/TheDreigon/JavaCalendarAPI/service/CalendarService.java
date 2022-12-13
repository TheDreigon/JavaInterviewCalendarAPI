package com.github.TheDreigon.JavaCalendarAPI.service;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;

import java.util.List;

/**
 * Common interface for calendar services, provides methods to manage calendars
 */
public interface CalendarService {

    /**
     * Get all calendars
     *
     * @return all calendars
     */
    List<Calendar> getCalendarList();

    /**
     * Retrieves a calendar object for the given calendar ID
     *
     * @param id the calendar ID
     * @return the calendar object
     */
    Calendar getCalendar(Integer id);

    /**
     * Creates a calendar object
     *
     * @param calendar the calendar object
     * @return the created calendar object
     */
    Calendar createCalendar(Calendar calendar);

    /**
     * Updates a calendar object
     *
     * @param calendar the calendar object
     * @return the updated calendar object
     */
    Calendar updateCalendar(Calendar calendar);

    /**
     * Deletes a calendar object for the given calendar ID
     *
     * @param id the calendar ID
     */
    void deleteCalendar(Integer id);
}
