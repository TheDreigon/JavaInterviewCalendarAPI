package com.github.TheDreigon.JavaCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaCalendarAPI.dto.CalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CalendarDto} to {@link Calendar} type conversion
 */
@Component
public class CalendarDtoToCalendar implements Converter<CalendarDto, Calendar> {

    @Autowired
    private CalendarService calendarService;

    /**
     * Converts the calendar DTO into a calendar model object
     *
     * @param calendarDto the calendar DTO to convert
     * @return the calendar
     */
    @Override
    public Calendar convert(CalendarDto calendarDto) {

        Calendar calendar = (calendarDto.getId() != null ? calendarService.getCalendar(calendarDto.getId()) : new Calendar());

        calendar.setName(calendarDto.getName());
        calendar.setDescription(calendarDto.getDescription());

        return calendar;
    }
}
