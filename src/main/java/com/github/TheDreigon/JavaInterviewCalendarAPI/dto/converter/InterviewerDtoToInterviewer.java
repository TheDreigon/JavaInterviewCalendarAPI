package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link InterviewerDto} to {@link Interviewer} type conversion
 */
@Component
public class InterviewerDtoToInterviewer implements Converter<InterviewerDto, Interviewer> {

    /**
     * Converts the interviewer DTO into an interviewer model object
     *
     * @param interviewerDto the interviewer DTO to convert
     * @return the interviewer
     */
    @Override
    public Interviewer convert(InterviewerDto interviewerDto) {

        Interviewer interviewer = new Interviewer();

        interviewer.setName(interviewerDto.getName());
        interviewer.setDescription(interviewerDto.getDescription());
        interviewer.setInterviewerAvailabilityList(interviewerDto.getInterviewerAvailabilityList());

        return interviewer;
    }
}
