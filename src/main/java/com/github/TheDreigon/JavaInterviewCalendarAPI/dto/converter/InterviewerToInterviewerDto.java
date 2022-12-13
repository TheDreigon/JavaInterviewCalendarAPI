package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Interviewer} to {@link InterviewerDto} type conversion
 */
@Component
public class InterviewerToInterviewerDto implements Converter<Interviewer, InterviewerDto> {

    /**
     * Converts the interviewer model object into an interviewer DTO
     *
     * @param interviewer the interviewer to convert
     * @return the interviewer DTO
     */
    @Override
    public InterviewerDto convert(Interviewer interviewer) {

        InterviewerDto interviewerDto = new InterviewerDto();

        interviewerDto.setId(interviewer.getId());
        interviewerDto.setName(interviewer.getName());
        interviewerDto.setDescription(interviewer.getDescription());

        return interviewerDto;
    }
}
