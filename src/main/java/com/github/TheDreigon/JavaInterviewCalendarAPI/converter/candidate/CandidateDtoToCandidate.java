package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.candidate;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.candidate.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateDto} to {@link Candidate} type conversion
 */
@Component
public class CandidateDtoToCandidate implements Converter<CandidateDto, Candidate> {

    @Autowired
    private CandidateAvailabilityDtoToCandidateAvailability candidateAvailabilityDtoToCandidateAvailability;

    /**
     * Converts the candidate DTO into a candidate model object
     *
     * @param candidateDto the candidate DTO to convert
     * @return the candidate
     */
    @Override
    public Candidate convert(CandidateDto candidateDto) {

        Candidate candidate = new Candidate();

        candidate.setName(candidateDto.getName());
        candidate.setDescription(candidateDto.getDescription());
        candidate.setCandidateAvailabilityList(new ArrayList<>());

        return candidate;
    }
}
