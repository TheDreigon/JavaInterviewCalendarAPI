package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerDtoNoList;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Interviewer} to {@link InterviewerDtoNoList} type conversion
 */
@Component
public class InterviewerToInterviewerDtoNoList implements Converter<Interviewer, InterviewerDtoNoList> {

    /**
     * Converts the interviewer model object into an interviewer DTO with no availability list
     *
     * @param interviewer the interviewer to convert
     * @return the interviewer DTO with no availability list
     */
    @Override
    public InterviewerDtoNoList convert(Interviewer interviewer) {

        InterviewerDtoNoList interviewerDtoNoList = new InterviewerDtoNoList();

        interviewerDtoNoList.setId(interviewer.getId());
        interviewerDtoNoList.setName(interviewer.getName());
        interviewerDtoNoList.setDescription(interviewer.getDescription());

        return interviewerDtoNoList;
    }
}
