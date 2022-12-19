package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link InterviewerAvailability} to {@link InterviewerAvailabilityDto} type conversion
 */
@Component
public class InterviewerAvailabilityToInterviewerAvailabilityDto implements Converter<InterviewerAvailability, InterviewerAvailabilityDto> {

    /**
     * Converts the interviewerAvailability model object into an interviewerAvailability DTO
     *
     * @param interviewerAvailability the interviewerAvailability to convert
     * @return the interviewerAvailability DTO
     */
    @Override
    public InterviewerAvailabilityDto convert(InterviewerAvailability interviewerAvailability) {

        InterviewerAvailabilityDto interviewerAvailabilityDto = new InterviewerAvailabilityDto();

        interviewerAvailabilityDto.setId(interviewerAvailability.getId());

        interviewerAvailabilityDto.setInterviewer(interviewerAvailability.getInterviewer());
        interviewerAvailabilityDto.setDayDate(interviewerAvailability.getDayDate());
        interviewerAvailabilityDto.setAvailableHour(interviewerAvailability.getAvailableHour());
        interviewerAvailabilityDto.setDayOfWeek(interviewerAvailability.getDayOfWeek());

        return interviewerAvailabilityDto;
    }
}
