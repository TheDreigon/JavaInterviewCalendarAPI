package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link InterviewerAvailabilityDto} to {@link InterviewerAvailability} type conversion
 */
@Component
public class InterviewerAvailabilityDtoToInterviewerAvailability implements Converter<InterviewerAvailabilityDto, InterviewerAvailability> {

    /**
     * Converts the interviewerAvailability DTO into an interviewerAvailability model object
     *
     * @param interviewerAvailabilityDto the interviewerAvailability DTO to convert
     * @return the interviewerAvailability
     */
    @Override
    public InterviewerAvailability convert(InterviewerAvailabilityDto interviewerAvailabilityDto) {

        InterviewerAvailability interviewerAvailability = new InterviewerAvailability();

        interviewerAvailability.setInterviewer(interviewerAvailabilityDto.getInterviewer());
        interviewerAvailability.setDayDate(interviewerAvailabilityDto.getDayDate());
        interviewerAvailability.setAvailableHour(interviewerAvailabilityDto.getAvailableHour());
        interviewerAvailability.setDayOfWeek(interviewerAvailabilityDto.getDayOfWeek());

        return interviewerAvailability;
    }
}
