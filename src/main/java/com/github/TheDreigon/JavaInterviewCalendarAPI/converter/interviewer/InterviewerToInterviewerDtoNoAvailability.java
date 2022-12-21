package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerDtoNoAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Interviewer} to {@link InterviewerDtoNoAvailability} type conversion
 */
@Component
public class InterviewerToInterviewerDtoNoAvailability implements Converter<Interviewer, InterviewerDtoNoAvailability> {

    /**
     * Converts the interviewer model object into an interviewer DTO with no availability list
     *
     * @param interviewer the interviewer to convert
     * @return the interviewer DTO with no availability list
     */
    @Override
    public InterviewerDtoNoAvailability convert(Interviewer interviewer) {

        InterviewerDtoNoAvailability interviewerDtoNoAvailability = new InterviewerDtoNoAvailability();

        interviewerDtoNoAvailability.setId(interviewer.getId());
        interviewerDtoNoAvailability.setName(interviewer.getName());
        interviewerDtoNoAvailability.setDescription(interviewer.getDescription());

        return interviewerDtoNoAvailability;
    }
}
