package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Converter} implementation, responsible for {@link Interviewer} to {@link InterviewerDto} type conversion
 */
@Component
public class InterviewerToInterviewerDto implements Converter<Interviewer, InterviewerDto> {

    @Autowired
    private InterviewerAvailabilityToAvailabilityDto interviewerAvailabilityToAvailabilityDto;

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

        List<AvailabilityDto> availabilityDtoList = new ArrayList<>();
        interviewer.getInterviewerAvailabilityList().forEach(e -> availabilityDtoList.add(interviewerAvailabilityToAvailabilityDto.convert(e)));
        interviewerDto.setAvailabilityDtoList(availabilityDtoList);

        return interviewerDto;
    }
}
