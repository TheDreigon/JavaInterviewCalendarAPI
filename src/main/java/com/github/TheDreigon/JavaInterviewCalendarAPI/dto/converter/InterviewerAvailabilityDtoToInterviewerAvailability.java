package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link InterviewerAvailabilityDto} to {@link InterviewerAvailability} type conversion
 */
@Component
public class InterviewerAvailabilityDtoToInterviewerAvailability implements Converter<InterviewerAvailabilityDto, InterviewerAvailability> {

    @Autowired
    private InterviewerAvailabilityService interviewerAvailabilityService;

    /**
     * Converts the interviewerAvailability DTO into an interviewerAvailability model object
     *
     * @param interviewerAvailabilityDto the interviewerAvailability DTO to convert
     * @return the interviewerAvailability
     */
    @Override
    public InterviewerAvailability convert(InterviewerAvailabilityDto interviewerAvailabilityDto) {

        InterviewerAvailability interviewerAvailability = (interviewerAvailabilityDto.getId() != null ? interviewerAvailabilityService.getInterviewerAvailability(interviewerAvailabilityDto.getId()) : new InterviewerAvailability());

        //interviewerAvailability.setName(interviewerAvailabilityDto.getName());
        //interviewerAvailability.setDescription(interviewerAvailabilityDto.getDescription());

        return interviewerAvailability;
    }
}
