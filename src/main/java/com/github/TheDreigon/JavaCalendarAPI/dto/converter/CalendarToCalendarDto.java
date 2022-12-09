package com.github.TheDreigon.JavaCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaCalendarAPI.dto.CalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Calendar} to {@link CalendarDto} type conversion
 */
@Component
public class CalendarToCalendarDto implements Converter<Calendar, CalendarDto> {

    /**
     * Converts the calendar model object into a calendar DTO
     *
     * @param calendar the calendar to convert
     * @return the calendar DTO
     */
    @Override
    public CalendarDto convert(Calendar calendar) {

        CalendarDto calendarDto = new CalendarDto();

        calendarDto.setId(calendar.getId());
        calendarDto.setName(calendar.getName());
        calendarDto.setDescription(calendar.getDescription());

        return calendarDto;
    }
}
