package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link InterviewerDto} to {@link Interviewer} type conversion
 */
@Component
public class InterviewerDtoToInterviewer implements Converter<InterviewerDto, Interviewer> {

    @Autowired
    private InterviewerService interviewerService;

    /**
     * Converts the interviewer DTO into an interviewer model object
     *
     * @param interviewerDto the interviewer DTO to convert
     * @return the interviewer
     */
    @Override
    public Interviewer convert(InterviewerDto interviewerDto) {

        Interviewer interviewer = (interviewerDto.getId() != null ? interviewerService.getInterviewer(interviewerDto.getId()) : new Interviewer());

        interviewer.setName(interviewerDto.getName());
        interviewer.setDescription(interviewerDto.getDescription());

        return interviewer;
    }
}
