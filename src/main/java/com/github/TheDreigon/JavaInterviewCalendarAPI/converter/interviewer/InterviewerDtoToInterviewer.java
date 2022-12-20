package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Converter} implementation, responsible for {@link InterviewerDto} to {@link Interviewer} type conversion
 */
@Component
public class InterviewerDtoToInterviewer implements Converter<InterviewerDto, Interviewer> {

    @Autowired
    private InterviewerAvailabilityDtoToInterviewerAvailability interviewerAvailabilityDtoToCandidateAvailability;

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
        interviewer.setInterviewerAvailabilityList(new ArrayList<>());

        return interviewer;
    }
}
