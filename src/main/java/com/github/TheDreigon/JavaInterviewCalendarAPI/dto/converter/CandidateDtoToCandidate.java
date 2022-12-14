package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateDto} to {@link Candidate} type conversion
 */
@Component
public class CandidateDtoToCandidate implements Converter<CandidateDto, Candidate> {

    @Autowired
    private CandidateService candidateService;

    /**
     * Converts the candidate DTO into a candidate model object
     *
     * @param candidateDto the candidate DTO to convert
     * @return the candidate
     */
    @Override
    public Candidate convert(CandidateDto candidateDto) {

        Candidate candidate = (candidateDto.getId() != null ? candidateService.getCandidate(candidateDto.getId()) : new Candidate());

        candidate.setName(candidateDto.getName());
        candidate.setDescription(candidateDto.getDescription());

        return candidate;
    }
}
