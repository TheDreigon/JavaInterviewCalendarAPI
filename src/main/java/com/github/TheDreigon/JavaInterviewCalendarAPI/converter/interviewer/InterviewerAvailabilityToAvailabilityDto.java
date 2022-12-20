package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link InterviewerAvailability} to {@link AvailabilityDto} type conversion
 */
@Component
public class InterviewerAvailabilityToAvailabilityDto implements Converter<InterviewerAvailability, AvailabilityDto> {

    /**
     * Converts the interviewerAvailability model object into an availability DTO
     *
     * @param interviewerAvailability the interviewerAvailability to convert
     * @return the availability DTO
     */
    @Override
    public AvailabilityDto convert(InterviewerAvailability interviewerAvailability) {

        AvailabilityDto availabilityDto = new AvailabilityDto();

        availabilityDto.setId(interviewerAvailability.getId());
        availabilityDto.setDayDate(interviewerAvailability.getDayDate());
        availabilityDto.setAvailableHour(interviewerAvailability.getAvailableHour());
        availabilityDto.setDayOfWeek(interviewerAvailability.getDayOfWeek());

        return availabilityDto;
    }
}
