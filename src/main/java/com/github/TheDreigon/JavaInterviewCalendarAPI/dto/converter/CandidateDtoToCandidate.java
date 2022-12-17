package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateDto} to {@link Candidate} type conversion
 */
@Component
public class CandidateDtoToCandidate implements Converter<CandidateDto, Candidate> {

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

        return candidate;
    }
}
